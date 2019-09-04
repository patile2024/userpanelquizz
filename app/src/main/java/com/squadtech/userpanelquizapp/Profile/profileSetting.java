package com.squadtech.userpanelquizapp.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squadtech.userpanelquizapp.MainActivity;
import com.squadtech.userpanelquizapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class profileSetting extends AppCompatActivity
{

    private ImageButton uploadprofileBtn ;
    private ImageView currentProfilePicture;
    private static int GallaryPick = 1;

    private Uri imageUri;
    private StorageReference myImageRef;
    private DatabaseReference mRootRef;
    private String  downloadImgUrl;
    boolean exists = false;
    private Button save_profile_changes_btnID;
    private EditText change_username,changePhone;

private TextView myEmailtxt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        uploadprofileBtn = (ImageButton)findViewById(R.id.upload_profile_picture);
        currentProfilePicture = (ImageView)findViewById(R.id.currentProfileImage);
        myEmailtxt = (TextView)findViewById(R.id.myEmailtxt);
        change_username = (EditText)findViewById(R.id.change_username);
        changePhone = (EditText)findViewById(R.id.change_phonenumber);
        save_profile_changes_btnID = (Button)findViewById(R.id.save_profile_changes_btnID);

        save_profile_changes_btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateName();
            }
        });
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String username = dataSnapshot.child("user_name").getValue().toString();
                String userPhone = dataSnapshot.child("user_phone").getValue().toString();
                     String getEmail = dataSnapshot.child("user_email").getValue().toString();
                change_username.setText(username);
                changePhone.setText(userPhone);
                myEmailtxt.setText(getEmail);

                // Picasso.get().load(getimage).placeholder(R.drawable.profile).into(myImageview);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        uploadprofileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGalleryImage();

            }
        });

        checkImage();

    }
    private void OpenGalleryImage() {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GallaryPick);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GallaryPick && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            currentProfilePicture.setImageURI(imageUri);

            UploadImage();

        }
    }
    private void UploadImage() {
        myImageRef = FirebaseStorage.getInstance().getReference().child("Users").child("user_image");
        mRootRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());

        final StorageReference filepath = myImageRef.child(FirebaseAuth.getInstance().getUid() + ".jpg");


        final UploadTask uploadTask = filepath.putFile(imageUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(profileSetting.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(profileSetting.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        downloadImgUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }


                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImgUrl = task.getResult().toString();


                            Toast.makeText(profileSetting.this, "Getting Url of Image", Toast.LENGTH_SHORT).show();

                         //   mRootRef.setValue(downloadImgUrl);
                                ChangeProfile();


                        }
                    }
                });
            }
        });
    }

    private void ValidateName() {
        FirebaseUser current_user_id = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = current_user_id.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("user_name", change_username.getText().toString());
        userMap.put("user_phone", changePhone.getText().toString());

        ref.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(profileSetting.this, "Settings Update Successfully", Toast.LENGTH_SHORT).show();
                }
                if (!task.isSuccessful()) {
                    task.getException();
                }


            }

        });
    }



        private void ChangeProfile() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("user_dp", downloadImgUrl);

        mRootRef.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(profileSetting.this, "Image Upload Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(profileSetting.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    // mprogressBar.dismiss();
                } else {
                    //mprogressBar.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(profileSetting.this, "Error " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkImage() {
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    exists = true;

                      String image_url = dataSnapshot.child("user_dp").getValue().toString();
                    Toast.makeText(profileSetting.this, "snap"+image_url, Toast.LENGTH_SHORT).show();

                    if (image_url != null && !image_url.equals("") && !image_url.equals("default")) {
                            Picasso.get().load(image_url).placeholder(R.drawable.ic_profile).into(currentProfilePicture);
                        }

                } else {
                    exists = false;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    }
