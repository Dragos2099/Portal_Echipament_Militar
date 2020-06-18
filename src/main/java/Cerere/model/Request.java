package Cerere.model;

public class Request {
    String Echipament;
    String Bucati;
    String Urgent;

    public Request() {
    }

    public Request(String echipament, String bucati, String urgent) {
        Echipament = echipament;
        Bucati = bucati;
        Urgent = urgent;
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

    public String getUrgent() {
        return Urgent;
    }

    public void setUrgent(String urgent) {
        Urgent = urgent;
    }

    @Override
    public String toString() {
        return "Request{" +
                "Echipament='" + Echipament + '\'' +
                ", Bucati='" + Bucati + '\'' +
                ", Urgent='" + Urgent + '\'' +
                '}';
    }
}
