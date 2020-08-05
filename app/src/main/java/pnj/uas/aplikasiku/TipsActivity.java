package pnj.uas.aplikasiku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pnj.uas.aplikasiku.adapter.AdapterTips;
import pnj.uas.aplikasiku.model.ModelTips;

public class TipsActivity extends AppCompatActivity {

    ListView listView;
    AdapterTips adapterTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        listView = findViewById(R.id.listTips);
        adapterTips= new AdapterTips(this, R.layout.item_tips);
        listView.setAdapter(adapterTips);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelTips data = (ModelTips) parent.getAdapter().getItem(position);

                Intent intent = new Intent(TipsActivity.this, DetailTipsActivity.class);
                intent.putExtra("imagetips", data.getImagetips());
                intent.putExtra("judultips", data.getJudultips());
                intent.putExtra("deskripsitips", data.getDeskripsitips());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        buatData();
    }

    void buatData() {
        adapterTips.clear();
        String judultips[] = new String[]{
                "13 Tips Makan Sehat untuk Orang yang Super Sibuk",
                "Tips Mengolah Makanan Sehat & Lezat"
        };
        String deskripsitips[] = new String[]{
                "Mungkin prinsip 4 sehat 5 sempurna telah menjadi slogan yang terus terngiang-ngiang dalam ingatan Anda. Slogan ini juga mungkin sudah Anda hafalkan sejak kecil. Namun, tahukah Anda bahwa slogan tersebut sudah tidak digunakan lagi saat ini? Mengapa begitu, ya?\n" +
                "\n" +
                "Slogan ini awalnya muncul sebagai kampanye pada tahun 1952. Sejak itu, 4 sehat 5 sempurna selalu menjadi prinsip utama bila ingin hidup sehat. Kemudian seiring berjalannya waktu dan berkembangnya ilmu pengetahuan, slogan ini tidak sesuai lagi dengan kehidupan di zaman sekarang.\n" +
                "\n" +
                "Oleh karena itu, Kementerian Kesehatan Indonesia telah mengeluarkan penggantinya, yaitu Pedoman Gizi Seimbang (PGS). Prinsip makan ini memang baru dikeluarkan dan disahkan pada tahun 2014 lalu, sehingga belum banyak yang tahu bahwa 4 sehat 5 sempurna tidak berlaku lagi.\n" +
                "\n" +
                "Berikut adalah alasan mengapa slogan 4 sehat 5 sempurna dinilai kurang pas dan digantikan akhirnya dengan Pedoman Gizi Seimbang.\n" +
                "\n" +
                "1. Makan sehat saja tidak cukup\n" +
                "Dahulu, mungkin Anda berpikir jika ingin hidup sehat maka Anda tinggal memenuhi prinsip makan 4 sehat 5 sempurna. Ini berarti setiap makan harus ada makanan pokok, lauk yang terdiri dari protein hewani dan protein nabati, sayur, buah, dan susu sebagai penyempurnanya.\n" +
                "\n" +
                "Pada kenyataannya, makanan bukan satu-satunya penentu status kesehatan Anda. Memilih makanan sehat saja tidak cukup untuk menjaga kebugaran dan kesehatan Anda secara keseluruhan.\n" +
                "\n" +
                "Dalam pedoman yang baru ini, disebutkan beberapa anjuran lain bila Anda ingin hidup sehat. Di antaranya berolahraga rutin, mengatur porsi makan, memantau berat badan secara teratur, serta menjaga kebersihan diri dan lingkungan sekitar.\n" +
                "\n" +
                "2. Jika tak sempat, susu boleh menjadi pilihan terakhir\n" +
                "Bila Anda beranggapan bahwa menu diet sehat Anda baru akan sempurna kalau sudah minum susu, maka Anda keliru. Susu tentu baik untuk dimasukan dalam menu diet sehat Anda sehari-hari, tetapi sifatnya tidak selalu wajib. Pasalnya, susu memiliki kandungan yang sama seperti lauk protein hewani.\n" +
                "\n" +
                "Kandungan lain yang ada di susu seperti kalsium, fosfor, dan zat besi, juga bisa Anda temukan dalam berbagai protein hewani lainnya. Jadi Anda dapat minum susu pada situasi tertentu, misalnya ketika dalam satu hari nutrisi yang masuk ke dalam tubuh dirasa kurang.\n" +
                "\n" +
                "3. Tidak ada ketentuan porsi\n" +
                "Di dalam slogan makan makanan sehat yang lama, tidak ada ketentuan dan aturan seberapa banyak porsi makan dalam sehari. Padahal, ketentuan porsi makan sangatlah penting untuk mencegah Anda mengalami kegemukan dan terserang berbagai penyakit kronis.\n" +
                "\n" +
                "Sementara itu, pada Pedoman Gizi Seimbang Anda juga bisa mendapatkan pembagian porsi menu makanan sehat di dalam piring makan Anda.\n" +
                "\n" +
                "4. Bahan makanan harus bervariasi\n" +
                "Semakin banyak bahan menu makanan sehat yang Anda makan, semakin baik kandungan gizinya. Dalam Prinsip Gizi Seimbang, ditekankan juga untuk mengonsumsi beragam jenis bahan makanan.\n" +
                "\n" +
                "Tidak hanya bergantung dengan satu jenis makanan saja, seperti makanan pokok yang bisa diganti dengan jagung, mi, ubi, atau kentang – tak harus nasi terus. Sedangkan di dalam slogan yang terdahulu tidak ada pesan tersebut.\n" +
                "\n" +
                "5. Anda juga harus banyak minum air\n" +
                "Dalam 4 sehat 5 sempurna, tidak disebutkan bahwa Anda harus mengonsumsi air mineral. Padahal, pemenuhan cairan sangat penting untuk menjaga kecukupan cairan di dalam tubuh Anda. Cairan yang paling baik untuk memenuhi kebutuhan Anda hanyalah air mineral.\n" +
                "\n" +
                "Oleh karena itu, pada Pedoman Gizi Seimbang Anda dianjurkan untuk minum air putih setidaknya 8 gelas per hari atau sesuai dengan kebutuhan masing-masing.",

                "Trend gaya hidup sehat sudah mulai membudaya dikalangan masarakat. Seperti kesadaran akan pentingnya mengkonsumsi makanan yang sehat. Saat ini, beragam penyakit degeneratif  seperti hipertensi, diabetes, kanker, obesitas dan penyakit jantung semakin meningkat jumlahnya. Salah satu penyebab adalah akibat pola makan buruk.\n" +
                        "\n" +
                        "Lantas seperti apa makanan yang sehat? Makanan sehat harus memenuhi unsur gizi yang seimbang bagi tubuh. Makanan juga harus memberikan rasa kenyang, serta tidak mengandung mikroorganisme yang dapat menggangu kesehatan tubuh. Berikut ini tip yang bisa dijadikan pedoman para bunda di dalam menyusun menu sehat untuk keluarga.\n" +
                        "\n" +
                        "Memilih:\n" +
                        "\n" +
                        "1.  Pilih bahan makanan yang segar karena bahana pangan segar memiliki kualitas rasa dan nilai gizi yang lebih baik dibandingkan makanan beku atau kalengan.\n" +
                        "\n" +
                        "2.  Selalu membersihkan dan mencuci  bahan pangan yang akan diolah. Tujuannya untuk mengurangi kontaminasi mikroba, menghilangkan kotoran atau telur cacing yang menempel dipermukaan bahan pangan.\n" +
                        "\n" +
                        "3.  Hilangkan produk jeroan dalam daftar belanjaan para Bunda. Terutama bahan makanan yang mengandung tingkat kolestrol tinggi.\n" +
                        "\n" +
                        " \n" +
                        "Sayangi Jantung Anda\n" +
                        "Meski hanya berukuran sekepal tangan pemiliknya, jantung sangat berperan dalam kehidupan manusia.\n" +
                        "\n" +
                        "4.  Konsumsi banyak ikan, seperti tuna, kakap, salmon dan makerel. Jenis ikan kaya akan protein dan mengandung asam lemak omega 3 yang lebih tinggi dibanding jenis ikan lainnya.\n" +
                        "\n" +
                        "5.  Pilih produk daging atau ayam yang tidak banyak mengandung lemak, seperti daging ayam kampung atau bagian dada tanpa kulit. Untuk daging sapi bisa dipilih bagian has dalam maupun has luar. Jenis daging tetelan, samcan dan sandung lamur sebaiknya dikurangi karena lemaknya tinggi.\n" +
                        "\n" +
                        "6.  Untuk produk lemak, pilih jenis lemak nabati seperti minyak kanola, olive oil atau minyak biji bunga matahari.\n" +
                        "\n" +
                        "7.  Beli buah yang sedang musimnya karena akan lebih segar dan lebih murah.\n" +
                        "\n" +
                        "8.  Tinggalkan kebiasaan membeli minuman bersoda. Pilih air putih, atau jus buah segar yang lebih menyehatkan karena banyak menghandung vitamin, mineral dan serat.\n" +
                        "\n" +
                        "Mengolah:\n" +
                        "\n" +
                        "Mengolah sayuran sebaiknya direbus terlebih dahulu baru dipotong-potong, mengingat sayuran banyak mengandung vitamin B dan C yang larut di dalam air. Perhatikan saat merebus, sebaiknya air jangan terlalu banyak, cukup sampai bahan yang direbus terendam oleh air.\n" +
                        "Pilih metode memasak dikukus atau direbus, metode memasak ini lebih sehat dibanding menggoreng karena tidak menggunakan lemak. Makanan yang dikukus juga memiliki kandungan gizi lebih banyak karena nutrisi tidak banyak yang terbuang selama proses pemasakan.\n" +
                        "Memasak dengan metode tumis dengan sedikit lemak lebih disarankan karena dibandingkan makanan yang digorang dalam minyak banyak. Selain tinggi lemak, makanan digoreng dalam minyak banyak juga berkurang kandungan vitamin A,D,E dan K karena jenis vitamin ini larut di dalam lemak.\n" +
                        "Bahan pangan seperti ikan sebaiknya dimasak dengan cara dikukus. Di dalam ikan mengandung asam lemak omega-3 mudah sekali mengalami kerusakan akibat pemanasan seperti penggorengan akibat proses oksidasi.\n" +
                        "Gunakan metode memasak yang singkat dengan suhu pemanasan yang tidak terlalu tinggi. Ini karena zat gizi umumnya akan rusak dalam pemanasan suhu tinggi terutama golongan vitamin dan mineral.\n" +
                        "Pilih alat masak dari stainless steel, tembikar atau keramik yang tidak mudah tergores. Jika menggunakan alat masak dari plastik, pastikan alat tersebut berlabel food grade agar aman untuk kesehatan.\n" +
                        "Hindari pemberian bumbu masak seperti seperti vetsin secara berlebihan. Gunakan kaldu ikan, ayam atau daging karena di dalam daging mengandung senyawa glutamat alami yang bercita rasa lezat seperti MSG.\n" +
                        "Batasi penggunaan garam. Agar masakan tetap lezat meskipun sedikit garam bisa disiasati dengan penambahan bumbu alami, seperti bawang merah, bawang putih, lada, pala dan sedikit gula pasir.\n" +
                        "Agar tampilan makanan lebih menarik dan sehat, gunakan pewarna alami di dalam mengolah makanan, kue atau minuman. Seperti pewarna hijau dari perasan daun suji dan pandan, kuning dari kunyit, wortel atau labu kuning. Merah dari umbi bit atau kayu secang. Hijau dari bayam dan hitam dari biji kluwak.",


        };

        String imagetips[] = new String[] {
                "https://hellosehat.com/wp-content/uploads/2017/02/makanan-sehat-orang-sibuk.jpg",
                "https://www.sahabatnestle.co.id/uploads/media/article/0001/07/thumb_6309_article_image_723x480.jpeg"

        };


        ArrayList<ModelTips> datas = new ArrayList<>();

        for (int i=0; i<judultips.length; i++) {
            ModelTips data = new ModelTips();
            data.setJudultips(judultips[i]);
            data.setDeskripsitips(deskripsitips[i]);
            data.setImagetips(imagetips[i]);


            datas.add(data);

        }
        adapterTips.addAll(datas);
        adapterTips.notifyDataSetChanged();
    }
}
