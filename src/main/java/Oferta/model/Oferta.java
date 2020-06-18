package Oferta.model;

public class Oferta {
    String Echipament;
    String Bucati;
    String Pret;
    String Data;
    String Stare;

    public Oferta() {
    }

    public Oferta(String echipament, String bucati, String pret, String data, String stare) {
        Echipament = echipament;
        Bucati = bucati;
        Pret = pret;
        Data = data;
        Stare = stare;
    }

    public String getEchipament() {
        return Echipament;
    }

    public void setEchipament(String echipament) {
        Echipament = echipament;
    }

    public String getBucati() {
        return Bucati;
    }

    public void setBucati(String bucati) {
        Bucati = bucati;
    }

    public String getPret() {
        return Pret;
    }

    public void setPret(String pret) {
        Pret = pret;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getStare() {
        return Stare;
    }

    public void setStare(String stare) {
        Stare = stare;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "Echipament='" + Echipament + '\'' +
                ", Bucati='" + Bucati + '\'' +
                ", Pret='" + Pret + '\'' +
                ", Data='" + Data + '\'' +
                ", Stare='" + Stare + '\'' +
                '}';
    }
}
