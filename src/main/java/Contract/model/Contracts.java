package Contract.model;

public class Contracts {
    String data;
    String buc;
    String total;
    String echipamente;
    String Nr_f;
    String Nr_bm;

public Contracts(){}

    public Contracts(String data, String buc, String total, String echipamente, String nr_furnizor, String nr_baza) {
        this.data = data;
        this.buc = buc;
        this.total = total;
        this.echipamente = echipamente;
        this.Nr_f = nr_furnizor;
        this.Nr_bm = nr_baza;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBuc() {
        return buc;
    }

    public void setBuc(String buc) {
        this.buc = buc;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEchipamente() {
        return echipamente;
    }

    public void setEchipamente(String echipamente) {
        this.echipamente = echipamente;
    }

    public String getNr_f() {
        return Nr_f;
    }

    public void setNr_f(String nr_f) {
        Nr_f = nr_f;
    }

    public String getNr_bm() {
        return Nr_bm;
    }

    public void setNr_bm(String nr_bm) {
        Nr_bm = nr_bm;
    }

    @Override
    public String toString() {
        return "Contracts{" +
                "data='" + data + '\'' +
                ", buc='" + buc + '\'' +
                ", total='" + total + '\'' +
                ", echipamente='" + echipamente + '\'' +
                ", Nr_f='" + Nr_f + '\'' +
                ", Nr_bm='" + Nr_bm + '\'' +
                '}';
    }
}
