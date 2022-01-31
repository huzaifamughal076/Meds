package com.MP.meds.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.MP.meds.Adapters.MyDataAdapter;
import com.MP.meds.ModelClass.MyListData;
import com.MP.meds.ModelClass.User;
import com.MP.meds.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String[] paths = {"item 1", "item 2", "item 3"};

    ArrayList<User> Medicine = new ArrayList<>();
    ArrayList<String> Medicine_Name = new ArrayList<>();
    ArrayList<MyListData> arrayList = new ArrayList<>();
    String qty1;
    File file;

    SimpleDateFormat datePatternFormat= new SimpleDateFormat("dd-MM-yyyy hh:mm:a");

    RecyclerView recyclerView;
    String SelectedMedicine;


    AutoCompleteTextView editText;
    TextView CodeView;
    TextView Company_Name;
    TextView qty;
    TextView discount;
    Button addbtn,sendOrder;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        CodeView = findViewById(R.id.product_code);
        Company_Name = findViewById(R.id.company_name);
        addbtn = findViewById(R.id.bth);
        qty = findViewById(R.id.qty);
        discount = findViewById(R.id.discount);
        sendOrder = findViewById(R.id.send_Order);

        user = new User();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Medicine_Sheet");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    user = dataSnapshot.getValue(User.class);
                    Medicine.add(user);

                }

                for (int i = 0; i <= Medicine.size() - 1; i++) {

                    User user = Medicine.get(i);
                    Medicine_Name.add(user.getName());

                }


                ArrayAdapter<String> myadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, Medicine_Name);


                editText.setAdapter(myadapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                 SelectedMedicine = editText.getText().toString().trim();
                qty1 = qty.getText().toString().trim();
                String discount2 = discount.getText().toString().trim();


                if (SelectedMedicine.isEmpty()||qty1.isEmpty() || discount2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill the field", Toast.LENGTH_SHORT).show();
                }

                else {

                    // Query checkMedicine = myRef.orderByChild("Name").equalTo(SelectedMedicine);

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            try {

                                int j =0;

                                for (int i = 0; i<=snapshot.getChildrenCount(); i++)
                                {

                                    j++;
                                    String Names = snapshot.child(String.valueOf(i)).child("Name").getValue().toString();

                                    if (Names.equals(SelectedMedicine)) {
                                        String c_name = snapshot.child(String.valueOf(i)).child("Company").getValue(String.class);
                                        String p_code = snapshot.child(String.valueOf(i)).child("Product Code").getValue().toString();


//                                    Toast.makeText(getApplicationContext(), c_name, Toast.LENGTH_SHORT).show();
                                        Company_Name.setText(c_name);
                                        CodeView.setText(p_code);





                                        recyclerView = findViewById(R.id.recycular_view);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                                        arrayList.add(new MyListData(c_name,SelectedMedicine,p_code,qty1,discount2));

                                        MyDataAdapter adapter = new MyDataAdapter(MainActivity.this,arrayList);
                                        recyclerView.setAdapter(adapter);



                                    }

                                    if (Names.equals(SelectedMedicine)) break;


                                }



                            }catch (Exception e)
                            {
                                Toast.makeText(getApplicationContext()," No Such Medicine Exists ",Toast.LENGTH_SHORT).show();
                            }






                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            Toast.makeText(getApplicationContext(), "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();


                        }


                    });

                }

            };



        });



        sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (arrayList.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"PLace Order",Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(getApplicationContext(),"Order Placing",Toast.LENGTH_SHORT).show();
                    printPDF();
                    sharePDF();


                }

            }
        });








    }

  public void printPDF() {

      int x=30 ,y=145;
      int k=1;

      if(y>145)
      {
          k++;
      }
      PdfDocument pdfDocument = new PdfDocument();
      Paint paint = new Paint();
      Paint ForLinePaint = new Paint();

      PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(550, 850,k).create();
      PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
      Canvas canvas = myPage.getCanvas();
      paint.setTextSize(20f);
      paint.setColor(Color.rgb(0,50,250));
      paint.setTextAlign(Paint.Align.CENTER);

      canvas.drawText("Muller&Pips",275,20,paint);
      paint.setTextSize(8.5f);
      canvas.drawText("Username:",30,40,paint);
      canvas.drawText("Address:",30,55,paint);

      ForLinePaint.setStyle(Paint.Style.STROKE);
      ForLinePaint.setPathEffect(new DashPathEffect(new float[]{5,5},0));
      ForLinePaint.setStrokeWidth(2);
      canvas.drawLine(10,65,540,65,ForLinePaint);
      canvas.drawText("Order:",20,105,paint);


      int z=30;
      canvas.drawText("Company Name",z,115,paint);
      z=z+110;
      canvas.drawText("Medicine Name",z,115,paint);
      z=z+110;
      canvas.drawText("Product Code",z,115,paint);
      z=z+110;
      canvas.drawText("Quantity",z,115,paint);
      z=z+110;
      canvas.drawText("Discount",z,115,paint);




      for (int i = 0; i<=arrayList.size()-1;i++)
      {
          canvas.drawText(arrayList.get(i).getRecycle_company(),x,y,paint);
          x=x+110;

          canvas.drawText(arrayList.get(i).getRecycle_Medicine(),x,y,paint);
          x=x+110;

          canvas.drawText(arrayList.get(i).getRecycle_code(),x,y,paint);
          x=x+110;

          canvas.drawText(arrayList.get(i).getRecycle_qty(),x,y,paint);
          x=x+110;

          canvas.drawText(arrayList.get(i).getRecycle_discount(),x,y,paint);
          x=x+110;
          y= y+20;

          System.out.println("\n");
          x=30;

      }

      canvas.drawText("     Date:  "+datePatternFormat.format(new Date().getTime()),35,530,paint);

     pdfDocument.finishPage(myPage);
      file = new File(String.valueOf(this.getExternalFilesDir("/")),"InvoicefromMuller&Pips.pdf");

      try {
          pdfDocument.writeTo(new FileOutputStream(file));
      } catch (IOException e) {
          e.printStackTrace();
      }

      pdfDocument.close();
  }

  public void sharePDF()
  {

      if (!file.exists())
      {
          Toast.makeText(getApplicationContext(),"File don't available",Toast.LENGTH_SHORT).show();
          return;
      }

      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("text/plain");
      intent.putExtra(Intent.EXTRA_SUBJECT,"Invoice");
//      intent.putExtra(Intent.EXTRA_FROM_STORAGE,File.separator+"InvoicefromM&P.pdf");
      intent.putExtra(Intent.EXTRA_TEXT, TextUtils.concat("title", "body").toString());

      startActivity(Intent.createChooser(intent,"Share via: "));


  }

}


