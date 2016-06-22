package com.istandev.cekhalal.entity;

import com.orm.SugarRecord;

/**
 * Created by ADIK on 19/05/2016.
 */
public class IstilahBahan{
    String nama_bahan;
    String istilah_lain;
    Long id;

    public IstilahBahan(){
    }

    public IstilahBahan(String nama, String lain){
        this.nama_bahan = nama;
        this.istilah_lain = lain;
    }

    public Long getId() {
        return id;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }
    public String getIstilah_lain() {
        return istilah_lain;
    }
}


