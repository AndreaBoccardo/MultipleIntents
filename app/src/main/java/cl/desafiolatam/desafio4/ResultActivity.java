package cl.desafiolatam.desafio4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cl.desafiolatam.desafio4.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    String vinculo = "";
    Uri imagen;
    boolean imagenCargada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Picasso.get().load("https://blog.desafiolatam.com/wp-content/uploads/2019/11/desafio-latam-slogan-fondo-blanco-300x141.png").resize(450,200).centerCrop().into(binding.ivLogoResultAct);

        imagenCargada = getIntent().getExtras().getBoolean("ImagenCargada");
        vinculo = getIntent().getExtras().getString("Vinculo");
//        imagen = MainActivity.cam_uri;
        imagen = getIntent().getExtras().getParcelable("imagenUri");


        binding.btnAbrirWeb.setOnClickListener(view -> {
            openWebPage(vinculo);

        });

        binding.btnShare.setOnClickListener(view -> {
            onClickWhatsApp(view);
        });

        binding.btnLlamar.setOnClickListener(view -> {
            onClickCall();
        });

        binding.btnDireccion.setOnClickListener(view -> {
            openWebPage("https://www.google.com/maps/place/Desaf%C3%ADo+Latam+-+Rebeca+Matte/@-33.4415684,-70.6351076,15z/data=!4m5!3m4!1s0x0:0xf751f642c9bff478!8m2!3d-33.4415684!4d-70.6351076");
        });
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);

    }

    public void onClickWhatsApp(View view) {

        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        String estadoImagen;
        if(imagenCargada == true){
            estadoImagen = "La imagen ha sido cargada con exito";
        }
        else
        {
            estadoImagen = "La imagen no ha sido cargada";
        }
        String text = vinculo + "\n" + estadoImagen;
        waIntent.setPackage("com.whatsapp");
        if (waIntent != null) {
            waIntent.putExtra(Intent.EXTRA_STREAM, imagen);
            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));
        } else {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void onClickCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String numero = "tel:" + "12345678";
        intent.setData(Uri.parse(numero));
        startActivity(intent);
    }
}