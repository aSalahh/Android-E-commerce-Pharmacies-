package com.q_silver.talabatak.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.OrdersAdapter;
import com.q_silver.talabatak.data.local.ad.model.Order;
import com.q_silver.talabatak.databinding.FragmentCartBinding;
import com.q_silver.talabatak.view.fragment.BaseFragment;
import com.q_silver.talabatak.viewmodel.OrderViewModel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;


public class CartFragment extends BaseFragment {
    FragmentCartBinding fragmentCartBinding;
    OrderViewModel orderViewModel;
    OrdersAdapter ordersAdapter;
    double totalPrice;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        intiFragment();
        fragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, null, false);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentCartBinding.recyclerViewOrders.setLayoutManager(layoutManager);
        orderViewModel.getAllOrder().observe(Objects.requireNonNull(getActivity()), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orderData) {
                totalPrice=0.0;
                ordersAdapter = new OrdersAdapter(getActivity(), orderData);
                fragmentCartBinding.recyclerViewOrders.setAdapter(ordersAdapter);
                if (orderData.isEmpty()) {
                    fragmentCartBinding.submitOrder.setVisibility(View.GONE);
                    fragmentCartBinding.totalPrice.setVisibility(View.GONE);
                    fragmentCartBinding.total.setVisibility(View.GONE);
                    fragmentCartBinding.recyclerViewOrders.setVisibility(View.GONE);
                    fragmentCartBinding.isEmpetyIv.setVisibility(View.VISIBLE);
                    fragmentCartBinding.total.setVisibility(View.GONE);
                    fragmentCartBinding.totalPrice.setText("0.0");


                } else {
                    fragmentCartBinding.recyclerViewOrders.setVisibility(View.VISIBLE);
                    fragmentCartBinding.isEmpetyIv.setVisibility(View.GONE);
                    for (int i=0;i<orderData.size();i++){
                        totalPrice+=(orderData.get(i).getCount()*orderData.get(i).getPrice());
                        fragmentCartBinding.totalPrice.setText(new DecimalFormat("##.##").format(totalPrice)+" LE");

                    }
                }

                ordersAdapter.setOnItemClickListener(new OrdersAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Order order) {

                    }

                    @Override
                    public void onDelete(Order order) {
                      orderViewModel.delete(order);
                        fragmentCartBinding.totalPrice.setText("0.0");

                    }

                    @Override
                    public void onIncreaseCount(Order order) {
                        orderViewModel.increaseCount(order);


                    }

                    @Override
                    public void onDecreaseCount(Order order) {
                        orderViewModel.decrementCount(order);

                    }
                });

            }
        });


        fragmentCartBinding.submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderViewModel.deleteAllOrders();
//                fragmentCartBinding.totalPrice.setText("0.0");
//                Toasty.info(getActivity(), "Done.", Toast.LENGTH_SHORT, true).show();



            }
        });
        return fragmentCartBinding.getRoot();
    }





    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_cycle_activity_fl_frame, new HomeFragment());

    }
}