package com.android.demo1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.demo1.Helper.SQLHelper;
import com.android.demo1.Models.Shoe;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    EditText edtName, edtPrice;
    Button btnCreate;
    RecyclerView rev;

    SQLHelper sqlHelper;

    List<Shoe> shoes;

    ShoeAdapter2 shoeAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        refView();
        sqlHelper = new SQLHelper(MainActivity3.this);
        shoes = sqlHelper.getShoes();
        shoes.add(new Shoe("1", "a", 11));
        shoes.add(new Shoe("2", "b", 11));
        shoes.add(new Shoe("3", "c", 11));
        shoes.add(new Shoe("4", "d", 11));

        shoeAdapter2 = new ShoeAdapter2(shoes, MainActivity3.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity3.this, 2, RecyclerView.VERTICAL, false);

        rev.setAdapter(shoeAdapter2);
        rev.setLayoutManager(layoutManager);

        shoeAdapter2.setiOnClickShoe(new IOnClickShoe() {
            @Override
            public void clickMore(Shoe shoe, int position, View imgMore) {
                PopupMenu popupMenu = new PopupMenu(MainActivity3.this, imgMore);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setForceShowIcon(true);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.p_detail:
                                Toast.makeText(MainActivity3.this, "detail", Toast.LENGTH_SHORT).show();

                                return true;
                            case R.id.p_edit:
                                Toast.makeText(MainActivity3.this, "edit", Toast.LENGTH_SHORT).show();
                                //tạo ra dialog sửa,có các ô thông tin cũ
                                return true;
                            case R.id.p_del:
                                Toast.makeText(MainActivity3.this, "Delete", Toast.LENGTH_SHORT).show();
                                //tạo ra dialog builder
                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity3.this);
                                dialog.setTitle("Xác nhận xóa");
                                dialog.setMessage("Bạn có chắc muốn xóa không?");

                                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity3.this, "đã xóa", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                dialog.show();
                                return true;
                            default:
                                Toast.makeText(MainActivity3.this, "inappropriate choice", Toast.LENGTH_SHORT).show();
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                int price = Integer.parseInt(edtPrice.getText().toString().trim());
                Shoe shoe = new Shoe("", name, price);
                sqlHelper.insertShoe(shoe);

                shoes = sqlHelper.getShoes();
                shoeAdapter2.setShoes(shoes);
            }
        });


    }

    private void refView() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnCreate = findViewById(R.id.btnCreate);
        rev = findViewById(R.id.rev);
    }
}