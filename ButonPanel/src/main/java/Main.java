import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Buton Kontrol adında özel bir JButton sınıfı oluşturuldu.
class ButonKontrol extends JButton {
    //Degişkenler tanımlandı
    private String graphQLSchema;
    private Color aktifRenk;
    private Color pasifRenk;
    private boolean Aktiflik;
    private static ButonKontrol SonTiklanan;

    //Constructor yardımıyla text parametresi ile bir JButton örneği oluşturuldu.
    public ButonKontrol(String text) {
        super(text);
        //Özelliklere başlangıç atandı.
        this.graphQLSchema = "";//Gerçek adresini eklenecek kısım.
        //AktifRenk değişkenini renk olarak tanımlayıp magenta değerini verdik.
        this.aktifRenk = Color.magenta;
        //PasifRenk değişkenini renk olarak tanımlayıp white değerini verdik.
        this.pasifRenk = Color.WHITE;
        this.Aktiflik = false;

        PasifDurum();
        addActionListener(new ButtonClickListener());
    }

    public void setGraphQLSchema(String schema) {
        this.graphQLSchema = schema;
    }

    //Aktif renk ayarlamak için kullanılır
    public void setAktif(Color renk) {

        this.aktifRenk= renk;
    }

    //Pasif renk ayarlamak için kullanılır
    public void setPasif(Color renk) {

        this.pasifRenk= renk;
    }

    private void PasifDurum() {
        setBackground(pasifRenk);
        setText("Pasif Buton");
        Aktiflik = false;// Butonu pasif hale getirir
    }

    private void setAktifDurum() {
        setBackground(aktifRenk);
        setText("Aktif Buton");
        Aktiflik = true;// Butonu aktif hale getirir
        //Belirlediğiniz GraphQL şemasındaki bir işlemi gerçekleştirir ve konsola bir mesaj yazdırır.
        System.out.println("Çalışıyor: " + graphQLSchema);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Aktiflik) {
                setPasifDurumSimdikiHaricTumu();
                Aktiflik = false;
            }
            else {
                SonTiklanan();
                setAktifDurum();
                Aktiflik= true;
            }
        }
    }
    //Son tıklanan bilgisi saklanır
    private void SonTiklanan(){
        SonTiklanan = this;
    }
    //Buton tıklanan hariç diğerlerini pasif duruma getirir
    private void setPasifDurumSimdikiHaricTumu() {
        for (Component component : getParent().getComponents()) {
            if (component instanceof ButonKontrol ) {
                ButonKontrol  button = (ButonKontrol ) component;
                if (button != this) {
                    button.PasifDurum();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Button Kontrol Paneli Uygulaması");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(4, 4, 12, 12));

            //16 buton ButonKontrol değerine atanır
            ButonKontrol [] buttons = new ButonKontrol [16];

            //Döngü ile tıklanan kutuya atama yapılır
            for (int i = 0; i < 16; i++) {
                buttons[i] = new ButonKontrol ("Button " + (i + 1));
                frame.add(buttons[i]);
                //Her buton belirlediğimiz "https://www.klu.edu.tr/" sitesine gider
                buttons[i].setGraphQLSchema("https://www.klu.edu.tr");
            }

            //Pencerenin genişliğini 500,yüksekliğini ise 500 tanımladık.
            frame.setSize(500, 500);
            //Kullanıcı tarafından görülebilir hale gelmesini sağlar.
            frame.setVisible(true);
        });
    }
}