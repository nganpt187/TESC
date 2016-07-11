package data;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by trong_000 on 6/29/2016.
 */
public class Main {
	
    public static boolean kiemTraDung(ArrayList<Cum> kiemtra) {
        int a = kiemtra.size();
        for (int i = 0; i < a; i++) {
            for (int j = i + 1; j < a; j++) {
                if (kiemtra.get(i).getNhancum() == kiemtra.get(j).getNhancum()) {
                    return false;
                }
            }
        }
        return true;
    }
    public static ArrayList<Cum> ganCum (String linkarff, String linkxml) {
        ArrayList<Cum> dscum = new ArrayList<Cum>();
        ArrayList<TheHien> dsthehienkhoitao = new ArrayList<TheHien>();
        ARFF filearff = new ARFF(linkarff);
        XML filexml = new XML(linkxml);
//        ARFF filearff = new ARFF("input/test.arff");
//        XML filexml = new XML("input/nhan.xml");
        int soluongnhan = filexml.getSoluongnhan();
        int i,j;


        // vong for nay dua cac the hien vao 1 list. cac the hien nay da dc chuyen sang tu dang rut gon.
        for ( i = 0; i < filearff.getSothehienbandau(); i++) {
            TheHien _thehien = new TheHien();
            ArrayList<Double> _ds = new ArrayList<Double>();
            for ( j = 0; j < filearff.getDsthuoctinh().size() - soluongnhan; j++ ) {
                _ds.add(filearff.getDsgiatri().get(j).get(i));
            }
            _thehien.setGiatri(_ds);
            dsthehienkhoitao.add(_thehien);
        }

        //vong for nay lay cac nhan dua vao ds nhan cua tung the hien.
        for ( i = 0; i < filearff.getSothehienbandau(); i++) {
            ArrayList<Double> _ds2 = new ArrayList<Double>();
            for ( j = filearff.getDsthuoctinh().size() - soluongnhan; j < filearff.getDsthuoctinh().size(); j++ ) {
                _ds2.add(filearff.getDsgiatri().get(j).get(i));
            }
            dsthehienkhoitao.get(i).setDsnhan(_ds2);
        }

        //vong for nay dua cac to hop trong file tohopnhom vao 1 list. tu list nay se xac dinh nhom moi cua cac cum.
        //xac dinh cac nhan moi cua cac cum. tuong ung voi danh sach tohopnhom lay ra o tren.
        for (i = 0; i < dsthehienkhoitao.size(); i++) {
            dsthehienkhoitao.get(i).setNhan(filexml);
        }

        dscum = new ArrayList<Cum>();
        for (i = 0; i < dsthehienkhoitao.size(); i++ ) {
            Cum cum = new Cum();
            cum.themTheHien(dsthehienkhoitao.get(i));
            cum.setNhancum(dsthehienkhoitao.get(i).getNhan());
            dscum.add(cum);
        }
        return dscum;
    }
    public static void main(String[] args) {
        int i,j;
//        ArrayList<Cum> dscum = Main.ganCum("input/test.arff","input/nhan.xml");
        ArrayList<Cum> dscum = Main.ganCum("input/example.arff","input/nhanexample.xml");
        ArrayList<Diem> phanbiet = new ArrayList<Diem>();
        while (!Main.kiemTraDung(dscum)) {
            Diem luuvitri = new Diem();
            double khoangcach = 100;

            for (i = 0; i < dscum.size(); i++) {
                for (j = i + 1; j < dscum.size(); j++) {
                    Diem k = new Diem();
                    k.setDiem(i,j);
                    if (!k.kiemTraPhanBiet(phanbiet)) {
                        double d = dscum.get(i).getKhoangCach(dscum.get(j));
                        if (d < khoangcach) {
                            khoangcach = d;
                            luuvitri.setDiem(i, j);// them cai setDiem(int a,int b) vao .
                        }
                    }
                }
            }
            //System.out.println(khoangcach);
            //System.out.println(luuvitri.getA() + " " + luuvitri.getB());
            if (dscum.get(luuvitri.getA()).getNhancum() == dscum.get(luuvitri.getB()).getNhancum()) {
                //System.out.println("bang nhau");
                dscum.get(luuvitri.getA()).gopCum(dscum.get(luuvitri.getB()));
                dscum.remove(luuvitri.getB());
                phanbiet.clear();
            } else {
                //System.out.println("khac");
                if (dscum.get(luuvitri.getA()).getNhancum() == 0) {
                    dscum.get(luuvitri.getB()).gopCum(dscum.get(luuvitri.getA()));
                    dscum.remove(luuvitri.getA());
                    phanbiet.clear();
                } else {
                    if (dscum.get(luuvitri.getB()).getNhancum() == 0) {
                        dscum.get(luuvitri.getA()).gopCum(dscum.get(luuvitri.getB()));
                        dscum.remove(luuvitri.getB());
                        phanbiet.clear();
                    } else
                        phanbiet.add(luuvitri);
                    }

            }
            //System.out.println("phan biet :"+phanbiet);
            //System.out.println("");
        }
        for ( i = 0; i < dscum.size(); i++) {
            dscum.get(i).inThongTinCum();
        }
        ArrayList <Cum> dscumkonhan = Main.ganCum("input/exampleunlabel.arff","input/nhanexampletrong.xml");
        double min ;
        int index;
        for ( i = 0; i < dscumkonhan.size(); i++) {
            min = dscumkonhan.get(i).getKhoangCach(dscum.get(0));
            index = 0;
            for ( j = 0; j < dscum.size(); j++) {
                if (dscumkonhan.get(i).getKhoangCach(dscum.get(j)) < min) {
                    min = dscumkonhan.get(i).getKhoangCach(dscum.get(j));
                    index = j;
                }
            }
            dscumkonhan.get(i).setNhancum(index+1);
        }


        //tu so nhan ghi vao file nhan.txt cac to hop nhom
        File file = new File("input/output.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for ( i = 0; i < dscumkonhan.size(); i++) {
                bw.write(dscumkonhan.get(i).inThongTinCumChuoi());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}