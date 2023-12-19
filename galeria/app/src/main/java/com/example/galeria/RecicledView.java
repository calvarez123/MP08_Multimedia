package com.example.galeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecicledView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycledview);

        List<Uri> imageUris = retrieveImageUris();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        CustomAdapter adapter = new CustomAdapter(imageUris);
        recyclerView.setAdapter(adapter);
    }

    private List<Uri> retrieveImageUris() {
        List<Uri> imageUris = new ArrayList<>();

        // Obtener el directorio de almacenamiento externo para imágenes
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Verificar si el directorio no es nulo y contiene archivos
        if (storageDir != null) {
            // Utilizar Java Stream para obtener Uris de archivos en el directorio
            imageUris = Arrays.stream(storageDir.listFiles())
                    .map(file -> Uri.fromFile(file))
                    .collect(Collectors.toList());
        }

        return imageUris;
    }

}
