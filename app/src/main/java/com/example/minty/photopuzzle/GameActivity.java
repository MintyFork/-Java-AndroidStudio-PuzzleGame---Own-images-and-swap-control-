package com.example.minty.photopuzzle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

class Title{    //Klasa płytki
    Bitmap bitmap;
    int x;
    int y;
    int numb;   //Numer bitmapy
    public Title(int a, int b){ //pos
        x = a;
        y = b;
    }
}

public class GameActivity extends AppCompatActivity {

    static int diff = 100;
    static int score = 0;
    int width;
    private ImageView imageView1; //Tworzenie zmiennej ImageView
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;

    Title t1 = new Title(1,1); //Nadajemy titles pozycje we wszechświecie
    Title t2 = new Title(2,1);
    Title t3 = new Title(3,1);
    Title t4 = new Title(1,2);
    Title t5 = new Title(2,2);
    Title t6 = new Title(3,2);
    Title t7 = new Title(1,3);
    Title t8 = new Title(2,3);
    Title t9 = new Title(3,3);
    ArrayList<Title> arrayList = new ArrayList<Title>(); //Lista obiektów

    private static int REQUEST_GALLERY = 1;
    private Bitmap bitmap; //Zmienna przechowujaca obrazek (bitmapy)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = 0;
        Display display = getWindowManager().getDefaultDisplay(); //Pobieram szerokość ekranu
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        arrayList.add(t1);  //Dadajemy obiekty do arreyList
        arrayList.add(t2);
        arrayList.add(t3);
        arrayList.add(t4);
        arrayList.add(t5);
        arrayList.add(t6);
        arrayList.add(t7);
        arrayList.add(t8);
        arrayList.add(t9);
        imageView1 = (ImageView) findViewById(R.id.ImageView1); //Łączymy naszą zmienna imageView z imageViev
        imageView2 = (ImageView) findViewById(R.id.ImageView2);
        imageView3 = (ImageView) findViewById(R.id.ImageView3);
        imageView4 = (ImageView) findViewById(R.id.ImageView4);
        imageView5 = (ImageView) findViewById(R.id.ImageView5);
        imageView6 = (ImageView) findViewById(R.id.ImageView6);
        imageView7 = (ImageView) findViewById(R.id.ImageView7);
        imageView8 = (ImageView) findViewById(R.id.ImageView8);
        imageView9 = (ImageView) findViewById(R.id.ImageView9);
        Intent intent = new Intent(); //Tworzymy nowy intent
        intent.setType("image/*");//Ustawiamy intenta typ na zdjęcia
        intent.setAction(Intent.ACTION_GET_CONTENT); //Pobieramy zawartość
        intent.addCategory(Intent.CATEGORY_OPENABLE); //Dodajemy kategorie
        startActivityForResult(intent, REQUEST_GALLERY); //Przekazujemy intencje i żadamy galleri

        mySwipe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK){ //Wczytywanie z galeri
            InputStream inputStream = null; //Tworzymy inputStream
            try{
                if(bitmap !=null ) bitmap.recycle();
                inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream); //Odekodowujemy strumień
                Matrix matrix = new Matrix(); //Tworzymy nowy matrix do skalowania
                matrix.postScale(((float) width)/ bitmap.getWidth(), ((float) width)/ bitmap.getWidth()); //Nadajemy skalę bitmapy
                Bitmap newbitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
                bitmap.recycle();
                makeRandom(newbitmap);
            }catch (Exception e){}
        }
    }

    Bitmap cropBitmap(Bitmap image, int x, int y){ //Ciecie obrazka
        return Bitmap.createBitmap(image, (image.getWidth()/3)*(x-1),(image.getHeight()/3)*(y-1),image.getWidth()/3, image.getHeight()/3);
    }

    public static Bitmap colorImage(int width, int height, int color) { //Bitmapa z koloru
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        return bitmap;
    }

    void makeRandom(Bitmap bitmap){ //Shuffle by Stanislawski
        int[] ar = { 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int magicNine = 8;
        Random rnd = new Random();
        for (int i = 0; diff > i; i++)
        {
            int index = rnd.nextInt(4);
                    if (index == 0 && ar[magicNine] == 9 && magicNine+1<9 && magicNine+1>-1 && magicNine!=2 && magicNine!=5) {
                        int a = ar[magicNine];
                        ar[magicNine] = ar[magicNine + 1];
                        ar[magicNine + 1] = a;
                        magicNine = magicNine+1;
                    } else if (index == 1 && ar[magicNine] == 9 && magicNine-1<9 && magicNine-1>-1 && magicNine!=3 && magicNine!=6){
                        int aa = ar[magicNine];
                        ar[magicNine] = ar[magicNine-1];
                        ar[magicNine-1] = aa;
                        magicNine = magicNine - 1;
                    } else if (index == 2 && ar[magicNine] == 9 && magicNine+3<9 && magicNine+3>-1){
                            int aaa = ar[magicNine];
                            ar[magicNine] = ar[magicNine+3];
                            ar[magicNine+3] = aaa;
                        magicNine = magicNine + 3;
                    } else if (index == 3 && ar[magicNine] == 9 && magicNine-3<9 && magicNine-3>-1){
                            int aaaa = ar[magicNine];
                            ar[magicNine] = ar[magicNine-3];
                            ar[magicNine-3] = aaaa;
                        magicNine = magicNine - 3;
                    }
        }

        t1.numb = ar[0]; //Nadajemy liczby losowe
        t2.numb = ar[1];
        t3.numb = ar[2];
        t4.numb = ar[3];
        t5.numb = ar[4];
        t6.numb = ar[5];
        t7.numb = ar[6];
        t8.numb = ar[7];
        t9.numb = ar[8];

        for (Title a : arrayList) //Nadajemy bitmapy do obiektów
        {
            if(a.numb == 9) a.bitmap = colorImage(bitmap.getWidth()/3,bitmap.getHeight()/3,0x00000000);
            if(a.numb == 1) a.bitmap = cropBitmap(bitmap,1,1);
            if(a.numb == 2) a.bitmap = cropBitmap(bitmap,2,1);
            if(a.numb == 3) a.bitmap = cropBitmap(bitmap,3,1);
            if(a.numb == 4) a.bitmap = cropBitmap(bitmap,1,2);
            if(a.numb == 5) a.bitmap = cropBitmap(bitmap,2,2);
            if(a.numb == 6) a.bitmap = cropBitmap(bitmap,3,2);
            if(a.numb == 7) a.bitmap = cropBitmap(bitmap,1,3);
            if(a.numb == 8) a.bitmap = cropBitmap(bitmap,2,3);
        }
        DrawView(); //Rysujemy w imageView
    }

    void DrawView(){
        imageView1.setImageBitmap(t1.bitmap);
        imageView2.setImageBitmap(t2.bitmap);
        imageView3.setImageBitmap(t3.bitmap);
        imageView4.setImageBitmap(t4.bitmap);
        imageView5.setImageBitmap(t5.bitmap);
        imageView6.setImageBitmap(t6.bitmap);
        imageView7.setImageBitmap(t7.bitmap);
        imageView8.setImageBitmap(t8.bitmap);
        imageView9.setImageBitmap(t9.bitmap);
        score++;
        isOver();
    }

    void isOver(){
        if(t1.numb == 1 && t2.numb == 2 && t3.numb == 3 && t4.numb == 4 && t5.numb == 5 && t6.numb == 6 && t7.numb == 7 && t8.numb == 8 && t9.numb == 9){
            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            startActivity(intent);
        }
    }

    private void mySwipe() {

        android.support.constraint.ConstraintLayout view = findViewById(R.id.root_view);

        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void swipeL() {
                loop:for (Title a : arrayList)
                {
                    if(a.numb == 9) {
                        for (Title b : arrayList){
                            if(a.y == b.y && b.x == a.x-1){
                                Bitmap bbb;
                                int num;
                                bbb = a.bitmap;
                                num = a.numb;
                                a.bitmap = b.bitmap;
                                a.numb = b.numb;
                                b.bitmap = bbb;
                                b.numb = num;
                                DrawView();
                                break loop;
                            }
                        }
                    }
                }
            }

            @Override
            public void swipeR() {
                loop:for (Title a : arrayList)
                {
                    if(a.numb == 9) {
                        for (Title b : arrayList){
                            if(a.y == b.y && b.x-1 == a.x){
                                Bitmap bbb;
                                int num;
                                bbb = a.bitmap;
                                num = a.numb;
                                a.bitmap = b.bitmap;
                                a.numb = b.numb;
                                b.bitmap = bbb;
                                b.numb = num;
                                DrawView();
                                break loop;
                            }
                        }
                    }
                }
            }

            @Override
            public void swipeT() {
                loop:for (Title a : arrayList)
                {
                    if(a.numb == 9) {
                        for (Title b : arrayList){
                            if(a.y-1 == b.y && b.x == a.x){
                                Bitmap bbb;
                                int num;
                                bbb = a.bitmap;
                                num = a.numb;
                                a.bitmap = b.bitmap;
                                a.numb = b.numb;
                                b.bitmap = bbb;
                                b.numb = num;
                                DrawView();
                                break loop;
                            }
                        }
                    }
                }
            }

            @Override
            public void swipeB() {
                loop:for (Title a : arrayList)
                {
                    if(a.numb == 9) {
                        for (Title b : arrayList){
                            if(a.y == b.y-1 && b.x == a.x){
                                Bitmap bbb;
                                int num;
                                bbb = a.bitmap;
                                num = a.numb;
                                a.bitmap = b.bitmap;
                                a.numb = b.numb;
                                b.bitmap = bbb;
                                b.numb = num;
                                DrawView();
                                break loop;
                            }
                        }
                    }
                }
            }
        });
    }
}
