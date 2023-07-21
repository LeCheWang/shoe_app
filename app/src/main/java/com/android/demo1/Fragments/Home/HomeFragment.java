package com.android.demo1.Fragments.Home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.demo1.Controllers.ApiController;
import com.android.demo1.Models.Shoe;
import com.android.demo1.R;
import com.android.demo1.databinding.FragmentHomeBinding;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }
    FragmentHomeBinding binding;
    List<Shoe> shoes = new ArrayList<>();
    ShoeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

//        shoes.add(new Shoe("Giầy thể thao", "https://anhdep123.com/wp-content/uploads/2021/01/anh-giay-adidas.jpg", 1000));
//        shoes.add(new Shoe("Giầy đá bòng", "https://anhdep123.com/wp-content/uploads/2021/01/anh-giay-adidas.jpg", 1000));
        //call api shoe
        callApiGetShoes();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog_create_shoe);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);

                EditText edtName, edtDes, edtPrice;
                Button btnCreate;

                edtName = dialog.findViewById(R.id.edtName);
                edtDes = dialog.findViewById(R.id.edtDes);
                edtPrice = dialog.findViewById(R.id.edtPrice);

                btnCreate = dialog.findViewById(R.id.btnCreate);

                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = edtName.getText().toString().trim();
                        String des = edtDes.getText().toString().trim();
                        String price = edtPrice.getText().toString().trim();
                        //check name > 6 kys tuwj
                        //call api
//                        Shoe shoe = new Shoe(name, des, Long.parseLong(price));
                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("name", name)
                                .addFormDataPart("description", des)
                                .addFormDataPart("price", price)
                                .build();

                        ApiController.apiService.createShoe(requestBody).enqueue(new Callback<Shoe>() {
                            @Override
                            public void onResponse(Call<Shoe> call, Response<Shoe> response) {
                                if (response.isSuccessful()){
                                    dialog.dismiss();
//                                    callApiGetShoes();
                                    shoes.add(response.body());
                                    adapter.setShoes(shoes);
                                }else {
                                    String err = "";
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                        err = jsonObject.getString("error");
                                    } catch (JSONException | IOException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(getActivity().getBaseContext(), "Có lỗi xảy ra: " + err, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Shoe> call, Throwable t) {
                                //lỗi không call được api
                                Toast.makeText(getActivity(), "Lỗi đường truyền", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                dialog.show();
            }
        });


        return binding.getRoot();
    }

    private void callApiGetShoes() {
        ApiController.apiService.getShoes().enqueue(new Callback<List<Shoe>>() {
            @Override
            public void onResponse(Call<List<Shoe>> call, Response<List<Shoe>> response) {
                if (response.isSuccessful()){
                    shoes = response.body();
                    adapter = new ShoeAdapter(shoes, getActivity());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);

                    binding.revShoes.setAdapter(adapter);
                    binding.revShoes.setLayoutManager(layoutManager);

                    adapter.setiOnClickShoe(new IOnClickShoe() {
                        @Override
                        public void iOnClickAddToCart(Shoe shoe, int position) {
                            Toast.makeText(getActivity(), shoe.getName() + " === " + shoe.getPrice(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    //xử lý lỗi mà server trả về
                }
            }

            @Override
            public void onFailure(Call<List<Shoe>> call, Throwable t) {
                //lỗi không call được api
                Toast.makeText(getActivity(), "Lỗi đường truyền", Toast.LENGTH_SHORT).show();
            }
        });
    }
}