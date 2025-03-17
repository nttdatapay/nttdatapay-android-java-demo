package com.example.demoaipaysdk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demoaipaysdk.databinding.ActivityMainBinding;
import com.ntt.ndpsaipaycheckout.PayActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view -> {
            String txnId = UUID.randomUUID().toString().replace("-","").substring(0,10);
            Toast.makeText(MainActivity.this,"Initiating NDPS transaction",Toast.LENGTH_SHORT).show();
            Intent newPayIntent=new Intent(MainActivity.this, PayActivity.class);

            newPayIntent.putExtra("merchantId", "317157");
            newPayIntent.putExtra("password", "Test@123");
            newPayIntent.putExtra("prodid", "NSE");
            newPayIntent.putExtra("signature_request", "KEY1234567234");
            newPayIntent.putExtra("signature_response", "KEYRESP123657234");
            newPayIntent.putExtra("enc_request", "A4476C2062FFA58980DC8F79EB6A799E");
            newPayIntent.putExtra("salt_request", "A4476C2062FFA58980DC8F79EB6A799E");
            newPayIntent.putExtra("salt_response", "75AEF0FA1B94B3C10D4F5B268F757F11");
            newPayIntent.putExtra("enc_response", "75AEF0FA1B94B3C10D4F5B268F757F11");
            newPayIntent.putExtra("txncurr", "INR");
            newPayIntent.putExtra("custacc", "100000036600");
            newPayIntent.putExtra("amt","1.00");
            newPayIntent.putExtra("txnid", txnId);
//            newPayIntent.putExtra("multi_products", createMultiProductData());
            newPayIntent.putExtra("isLive", false);//false true
            newPayIntent.putExtra("custFirstName", "test user");
            newPayIntent.putExtra("customerEmailID", "testuser@xyz.in");
            newPayIntent.putExtra("customerMobileNo", "8888888811");
            newPayIntent.putExtra("udf1", "udf1");
            newPayIntent.putExtra("udf2", "udf2");
            newPayIntent.putExtra("udf3", "udf3");
            newPayIntent.putExtra("udf4", "udf4");
            newPayIntent.putExtra("udf5", "udf5");
//                newPayIntent.putExtra("subChannel", "UP"); //To enable specific payment mode use this parameter else comment itout
            initNDPSPayments.launch(newPayIntent);
        });
    }

    ActivityResultLauncher<Intent> initNDPSPayments = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {});

    public String createMultiProductData() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjOne = new JSONObject();
        JSONObject jsonObjTwo = new JSONObject();
        try {
            jsonObjOne.put("prodName", "NSE");
            jsonObjOne.put("prodAmount", 1.00);
            jsonObjTwo.put("prodName", "AIPAY");
            jsonObjTwo.put("prodAmount", 1.00);
            jsonArray.put(jsonObjOne);
            jsonArray.put(jsonObjTwo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("jsonArray from createMultiProductData = " + jsonArray.toString());
        return jsonArray.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("resultCode = "+resultCode);
        System.out.println("onActivityResult data = "+data);

        if(data != null && resultCode != 2 && resultCode != 3) {
            System.out.println("IsIntent = "+ Objects.requireNonNull(data.getExtras()).getString("IsIntent"));
            System.out.println("ArrayList data = "+data.getExtras().getString("response"));
            Toast.makeText(this, data.getExtras().getString("response"), Toast.LENGTH_LONG).show();
            if (resultCode == 1) {
                Toast.makeText(MainActivity.this, "Transaction Successful! \n" + data.getExtras().getString("response"), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Transaction Failed! \n" + data.getExtras().getString("response"), Toast.LENGTH_LONG).show();
            }
        }else{
            if(resultCode == 2) {
                Toast.makeText(MainActivity.this,"Transaction Cancelled!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"Transaction Timeout!", Toast.LENGTH_LONG).show();
            }
        }
    }//onActivityResult

}