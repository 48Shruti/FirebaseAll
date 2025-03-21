package com.shruti.firebaseall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import com.shruti.firebaseall.databinding.ActivityPaymentBinding
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultWithDataListener, ExternalWalletListener {
    lateinit var binding : ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPay.setOnClickListener {
            startPayment()
        }
    }
    fun startPayment(){
        Checkout.preload(this)
        val co = Checkout()
        co.setKeyID("rzp_test_c51XgNNFUQLpLP")
        try {
            val options = JSONObject()
            options.put("name", "Shruti")
            options.put("image", R.drawable.ic_launcher_background)
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", "1000")

            val retryObject = JSONObject()
            retryObject.put("enables", true)
            retryObject.put("max_count", 4)
            retryObject.put("retry", retryObject)

            val prefill = JSONObject()
            prefill.put("email", "abc@gmail.com")
            prefill.put("contact","123456789")

            options.put("prefill", prefill)
            co.open(this, options)
        }catch (e:Exception){
            Toast.makeText(this, "Error in payment" + e.message,Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Log.d("Payment","Check payment successfully or not ${p0}")
        Log.d("Payment", "Check payment data ${p1}")
        Toast.makeText(this, "Payment is successfully " + p0, Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment Error is " + p1, Toast.LENGTH_SHORT).show()
    }

    override fun onExternalWalletSelected(p0: String?, p1: PaymentData?) {
        TODO("Not yet implemented")
    }
}