package cl.desafiolatam.desafio4;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cl.desafiolatam.desafio4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean imagenCargada = false;
    ActivityResultLauncher activityResultLauncher;
    private ActivityMainBinding binding;
    static Uri cam_uri;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Picasso.get().load("https://blog.desafiolatam.com/wp-content/uploads/2019/11/desafio-latam-slogan-fondo-blanco-300x141.png").resize(450,200).centerCrop().into(binding.ivLogoDLatam);

        //Se solicita permiso para acceder a la camara por medio de un ActivityResultLauncher.

        ActivityResultLauncher<String> cameraPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result){
                    Toast.makeText(getApplicationContext(),"Camera Permission Granted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Camera Permission not Granted",Toast.LENGTH_SHORT).show();
                }
            }
        });


        cameraPermission.launch(Manifest.permission.CAMERA);



        binding.btnCambiarImagen.setOnClickListener(view -> {

            pickCamera();

        });

        binding.btnSiguiente.setOnClickListener(view -> {



            if(imagenCargada == true){
                //Realizo intent
                Intent intent = new Intent(this,ResultActivity.class);
                intent.putExtra("Vinculo","http://www.desafiolatam.com");
                intent.putExtra("ImagenCargada",imagenCargada);
                intent.putExtra("imagenUri",cam_uri);

//                Bundle bundle = new Bundle();
//                bundle.putByteArray("imagenEnviar",cam_uri);

                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"La imagen se debe cargar para continuar",Toast.LENGTH_SHORT).show();
            }
        });

        //Cierre OnCreate
    }

    //Intent para abrir la camara, tomar foto y guardarla.

    public void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        cam_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY
    }

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // There are no request codes
                        imagenCargada = true;
                        binding.ivImagenPorCargar.setImageURI(cam_uri);

                    }
                }
            });




    //Cierre MainActivity
}