package com.istandev.cekhalal.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ADIK on 10/01/2016.
 */
public class Produk implements Parcelable{
    private String nama_produk;
    private String merek_produk;
    private String pabrik_produk;
    private String bahan_produk;
    private String status_produk;
    private String gambar_produk;


    public String getNamaProduk(){
        return nama_produk;
    }

    public String getMerekProduk(){
        return merek_produk;
    }

    public String getPabrik_produk(){
        return pabrik_produk;
    }

    public String getBahan_produk(){
        return bahan_produk;
    }

    public String getStatus_produk(){
        return status_produk;
    }

    public String getGambar_produk(){
        return gambar_produk;
    }


    public void setNamaProduk (String nama_produk){
        this.nama_produk = nama_produk;
    }

    public void setMerek_produk (String merek_produk){
        this.merek_produk = merek_produk;
    }

    public void setPabrik_produk (String pabrik_produk){
        this.pabrik_produk = pabrik_produk;
    }

    public void setBahan_produk (String bahan_produk){
        this.bahan_produk = bahan_produk;
    }

    public void setStatus_produk (String status_produk){
        this.status_produk = status_produk;
    }

    public void setGambar_produk (String gambar_produk){
        this.gambar_produk = gambar_produk;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Storing the Student data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama_produk);
        dest.writeString(merek_produk);
        dest.writeString(pabrik_produk);
        dest.writeString(bahan_produk);
        dest.writeString(status_produk);
        dest.writeString(gambar_produk);
    }

    /**
     * A constructor that initializes the Student object
     **/
    public Produk(String nama_produk,String merek_produk,String pabrik_produk,String bahan_produk, String status_produk, String gambar_produk){
        this.nama_produk= nama_produk;
        this.merek_produk = merek_produk;
        this.pabrik_produk = pabrik_produk;
        this.bahan_produk = bahan_produk;
        this.status_produk = status_produk;
        this.gambar_produk = gambar_produk;
    }

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private Produk(Parcel in){
        this.nama_produk = in.readString();
        this.merek_produk = in.readString();
        this.pabrik_produk = in.readString();
        this.bahan_produk = in.readString();
        this.status_produk = in.readString();
        this.gambar_produk = in.readString();
    }

    public static final Parcelable.Creator<Produk> CREATOR = new Parcelable.Creator<Produk>() {

        @Override
        public Produk createFromParcel(Parcel source) {
            return new Produk(source);
        }

        @Override
        public Produk[] newArray(int size) {
            return new Produk[size];
        }
    };
}
