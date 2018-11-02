package com.example.quizuno.itemlistcrud;

public class Item {

    String titulo;
    String descrip;
    String itemId;

    public Item(){

    }

    public Item(String titulo, String descrip, String itemId) {
        this.titulo = titulo;
        this.descrip = descrip;
        this.itemId = itemId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getItemId() {
        return itemId;
    }
}
