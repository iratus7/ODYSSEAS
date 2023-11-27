package com.example.odysseas;

//import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.database.Cursor;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {




    Context CTX = this;
    // boolean IsTurnedOn = false;
    //public static Camera cam = null;
    ImageButton buttonG;
    ImageButton DialButton;
    public Integer Fid;
    String PhNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "ΚΑΛΩΣ ΗΡΘΕΣ ΣΥΝΑΔΕΛΦΕ!!!", Toast.LENGTH_LONG).show();

        /////Check if pdf files exist
        File pdf1 = new File(CTX.getExternalFilesDir(null) + "/" + "1.pdf");
        if(pdf1.exists()) {
            //Do nothing
        }else{ CopyPdfFiles.copyAssets(CTX);}
        // copy pdf files.

        ///////////////////////////Load the database
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        ////Show welcome only first time App opens
//        SharedPreferences prefs = this.getSharedPreferences(
//                "com.example.odysseas", Context.MODE_PRIVATE);
//        boolean hasVisited = prefs.getBoolean("HAS_VISISTED_BEFORE", false);
//        if(!hasVisited) {
//            Toast.makeText(getApplicationContext(), "ΚΑΛΩΣ ΗΡΘΕΣ ΣΥΝΑΔΕΛΦΕ!!!", Toast.LENGTH_LONG).show();
//            prefs.edit().putBoolean("HAS_VISISTED_BEFORE", true).commit();
//        }

        final ArrayList<String> centerString = new ArrayList<String>(Arrays.asList("ΒΑΣΙΛΙΚΩΝ", "ΓΑΛΑΡΙΝΟΥ", "ΛΙΒΑΔΙ", "ΜΟΝΟΠΗΓΑΔΟΥ", "ΠΕΡΙΣΤΕΡΑΣ", "ΣΟΥΡΩΤΗΣ", "ΒΥΖΑΝΤΙΟ", "ΘΕΡΜΗΣ", "ΚΑΛΑΜΑΡΙΑΣ", "ΦΟΙΝΙΚΑΣ", "ΑΓ.ΤΡΙΑΔΑΣ", "ΑΓΓΕΛΟΧΩΡΙΟΥ", "ΕΠΑΝΩΜΗΣ", "ΜΕΣΗΜΕΡΙΟΥ", "ΜΗΧΑΝΙΩΝΑΣ", "ΠΕΡΑΙΑΣ", "ΡΥΣΙΟΥ", "ΤΑΓΑΡΑΔΕΣ", "ΤΟΥΜΠΑ ΕΠΑΝΩΜΗΣ", "ΤΡΙΛΟΦΟΥ", "ΕΞΟΧΗ", "ΠΑΝΟΡΑΜΑ", "ΤΟΥΜΠΑΣ", "ΧΑΡΙΛΑΟΥ", "ΧΟΡΤΙΑΤΗΣ", "ΡΟΣΤΑΝ", "ΑΜΠΕΛΟΚΗΠΟΙ ΘΣΝ", "ΕΛΕΥΘΕΡΙΑ", "ΕΥΚΑΡΠΙΑΣ", "ΠΑΥΛΟΥ ΜΕΛΑ", "ΩΡΑΙΟΚΑΣΤΡΟΥ", "ΑΣΠΡΟΒΑΛΤΑΣ", "ΜΑΔΥΤΟΥ", "ΡΕΝΤΙΝΑΣ-ΜΟΔΙ", "ΣΤΑΥΡΟΥ", "ΑΓ ΑΘΑΝΑΣΙΟΣ", "ΑΓΧΙΑΛΟΣ", "ΑΝΑΤΟΛΙΚΟ", "ΒΑΘΥΛΑΚΟΣ", "ΓΕΦΥΡΑ", "ΔΙΑΒΑΤΩΝ", "ΜΕΣΑΙΟ", "Ν.ΜΕΣΗΜΒΡΙΑ", "ΝΕΟΧΩΡΟΥΔΑ", "ΠΡΟΧΩΜΑ", "ΣΙΝΔΟΣ", "ΧΑΛΑΣΤΡΑ", "ΑΠ.ΠΑΥΛΟΥ", "ΕΡΜΟΥ", "ΑΓ.ΒΑΣΙΛΕΙΟΥ", "ΑΝΑΛΗΨΗΣ", "ΑΣΣΗΡΟΥ", "ΒΕΡΤΙΣΚΟΥ", "ΔΟΡΚΑΔΑΣ", "ΔΡΥΜΟΥ", "ΚΑΒΑΛΑΡΙ", "ΚΟΛΧΙΚΟΥ", "ΚΡΙΘΙΑΣ", "ΛΑΓΚΑΔΑ", "ΛΕΥΚΟΧΩΡΙ", "ΛΗΤΗΣ", "ΞΥΛΟΥΠΟΛΗΣ", "ΟΣΣΑΣ", "ΠΕΝΤΕ ΒΡΥΣΕΣ", "ΒΑΣΙΛΟΥΔΙ", "ΖΑΓΚΛΙΒΕΡΙ", "ΚΑΛΑΜΩΤΟΥ", "ΚΟΚΚΑΛΟΥ", "ΛΑΓΚΑΔΙΚΙΩΝ", "Ν.ΑΠΟΛΛΩΝΙΑΣ", "ΠΕΤΡΟΚΕΡΑΣΑ", "ΠΡΟΦΗΤΗ", "ΒΑΡΝΑΣ", "ΚΑΛΟΧΩΡΙ", "ΠΛΑΤ.ΔΗΜΟΚΡΑΤ.", "ΡΕΤΖΙΚΗ-ΠΕΥΚΩΝ", "ΦΙΛΥΡΟΥ", "ΑΡΕΘΟΥΣΑΣ", "ΑΡΕΤΗ", "ΑΣΚΟΥ", "ΚΡΥΟΝΕΡΙΟΥ", "ΛΟΦΙΣΚΟΣ", "ΜΑΥΡΟΥΔΑΣ", "ΣΟΧΟΥ", "ΦΙΛΑΔΕΛΦΕΙΟΥ", "ΑΔΕΝΔΡΟ", "ΑΘΥΡΑ", "ΚΟΥΦΑΛΙΑ", "ΚΥΜΙΝΑ", "ΜΙΜΡΟ ΜΟΝΑΣΤΗΡΙ", "ΧΑΛΚΗΔΟΝΑΣ"));

        final ArrayList<String> centerPhones = new ArrayList<String>(Arrays.asList("", "", "", "", "", "", "2310364415 - 2310364416 - 2310411499", "2310462299 - 2310461299", "2310364421 - 2310430499", "2310471799", "", "", "", "", "", "2310364107 - 2392022599 - 471599", "", "", "", "", "2310358699", "2310342199", "2310939999 - 2310912999", "2310364146 - 2310364147", "2310349099", "2310369802 - 2310369810 - 2310369818 - 2310824499", "2310362718 - 2310731299 - 2310731399", "2310362719 - 2310761299 - 2310761199", "2310362720 - 2310682999 - 2310681999", "2310362548 - 2310651199 - 2310651299", "2310698499 - 2310696199", "2397022299", "", "2397025799", "", "", "2310722299", "", "", "", "2310782299 - 2310574950", "", "", "", "", "2310797999 - 2310796599", "", "2310362227", "2310362222", "", "", "", "", "", "", "", "", "", "2394022599", "", "", "", "", "", "", "", "", "", "2393022299", "", "", "", "2310362549 - 2310610099 - 2310633899", "2310751299", "2310362528 - 2310523299 - 2310362547", "2310362525", "", "", "", "", "", "", "", "2395022299", "", "", "", "", "", "", "2391022099 - 2391022199 - 2391021100"));

        final Map<String, Integer> center_kv = new HashMap<String, Integer>();
        for (int i = 0; i < centerString.size(); i++) {
            center_kv.put(centerString.get(i), i + 1);
        }
        Collections.sort(centerString);
        final Spinner SP = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, centerString);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        SP.setAdapter(spinnerAdapter);

        final Spinner SP2 = (Spinner) findViewById(R.id.spinner2);

        final ArrayList<String> selectedKv = new ArrayList<String>();
        final ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, selectedKv);


        SP.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedKv.clear();
                        DataBaseHelper dop = new DataBaseHelper(CTX);
                        Cursor CR = dop.getInformation(dop);
                        String name_k = SP.getSelectedItem().toString();
                        Integer idS = center_kv.get(name_k);
                        CR.moveToFirst();
                        String KVOfSelected = "";
                        do {
                            if (idS.equals(Integer.parseInt(CR.getString(2)))) {
                                KVOfSelected = CR.getString(1);
                                selectedKv.add(KVOfSelected);
                            }
                        }
                        while (CR.moveToNext());
                        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SP2.setAdapter(spinnerAdapter2);
                        Fid = idS;

                        DialButton.setVisibility(View.INVISIBLE);
                        String phoneNumbers = centerPhones.get(idS - 1);
                        if (phoneNumbers.equals("")) {
                        } else {
                            PhNumber = phoneNumbers;
                            DialButton.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedKv.clear();
                        selectedKv.add("ΠΑΡΑΚΑΛΩ ΕΠΙΛΕΞΤΕ ΚΕΝΤΡΟ");
                        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SP2.setAdapter(spinnerAdapter2);
                    }
                }
        );
        buttonG = (ImageButton) findViewById(R.id.buttonGps);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper DOP = new DataBaseHelper(CTX);

                Cursor CR = DOP.getInformation(DOP);
                String Selected_kv = SP2.getSelectedItem().toString();
                Integer id = Fid;

                CR.moveToFirst();
                String FinalCoordinates = "";
                do {
                    if (id.equals(Integer.parseInt(CR.getString(2)))) {
                        if (Selected_kv.equals(CR.getString(1))) {
                            FinalCoordinates = CR.getString(3);
                        }
                    }
                }
                while (CR.moveToNext());
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + FinalCoordinates));
                startActivity(intent);
            }
        };


        buttonG.setOnClickListener(oclBtnOk);

        ///////  button information stoixeia diktyou
        ImageButton buttonInfo = (ImageButton) findViewById(R.id.buttonInfo);
        View.OnClickListener infoListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(CTX.getExternalFilesDir(null) + "/" + Fid + ".pdf");

                if (file.exists()) {

                    Uri pdfPath = FileProvider.getUriForFile(CTX, BuildConfig.APPLICATION_ID + ".fileprovider", file);

                    Intent target = new Intent(Intent.ACTION_VIEW);
                    target.setDataAndType(pdfPath, "application/pdf");
                    target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    target.createChooser(target, "Open pdf File");
                    //Intent intent = Intent.createChooser(target, "Open pdf File");
                    try {
                        startActivity(target);
                    } catch (ActivityNotFoundException e) {
                        // Instruct the user to install a PDF reader here, or something
                    }
                } else {
                    Toast.makeText(CTX, "File path is incorrect.", Toast.LENGTH_LONG).show();
                }
            }
        };
        buttonInfo.setOnClickListener(infoListen);


        /////////////dial button
        DialButton = (ImageButton) findViewById(R.id.DialButton);
        View.OnClickListener dialListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] phones = PhNumber.split(" - ");
                AlertDialog.Builder builder = new AlertDialog.Builder(CTX);
                builder.setTitle("Επιλογή Τηλεφώνου");
                builder.setItems(phones, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phones[item].toString()));
                        ////if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        //// return;
                        //// }
                        startActivity(intent);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        };
        DialButton.setOnClickListener(dialListen);


        ToggleButton FlB = (ToggleButton) findViewById(R.id.FlashBt);

        final boolean[] IsTurnedOn = {false};
        final CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = null; // Usually front camera is at 0 position.
        try {
            cameraId = camManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        final String finalCameraId = cameraId;

        FlB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!IsTurnedOn[0]) {
                    try {
                        camManager.setTorchMode(finalCameraId, true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        camManager.setTorchMode(finalCameraId, false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                IsTurnedOn[0] = !IsTurnedOn[0];


            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            try {
                Process process = Runtime.getRuntime().exec("cat /proc/cpuinfo");
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                // in.readLine();
                String cpuInfo=in.readLine();
                process = Runtime.getRuntime().exec("cat /proc/meminfo");
                in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                Integer mem=Integer.parseInt(in.readLine().replaceAll("\\D+",""));
                Double memGb= (double)mem /1048576;

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
                alertDialog.setTitle("ABOUT");
                alertDialog.setMessage("ΟΔΥΣΣΕΑΣ 3.0" + "\n" + "Model: " + Build.MANUFACTURER + " - " + Build.MODEL + "\n" + "Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")" + "\n" + "CPU" + cpuInfo.substring(11) + "\n" +"MEMORY: "+String.format("%.2f", memGb) +"Gb");

                alertDialog.setButton("ΣΥΝΕΧΕΙΑ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                    }
                });
                alertDialog.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
