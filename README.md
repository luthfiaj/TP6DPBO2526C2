# Flappy Bird - Java Swing GUI

**Tugas Praktikum 6 (TP6) - Desain dan Pemrograman Berorientasi Objek (DPBO)**

## Janji

Saya Luthfi Aulia Jodi dengan nim 2521743 mengerjakan TP6 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

---

## 1. Desain Program

Program ini merupakan game Flappy Bird sederhana yang dibuat menggunakan Java Swing. Cara bermainnya mirip dengan Flappy Bird pada umumnya, yaitu pemain harus menjaga burung agar tetap terbang dan melewati celah di antara dua pipa tanpa menabraknya.

Dalam pembuatannya, program dibagi menjadi beberapa class. Setiap class memiliki tugas masing-masing agar kode lebih terstruktur, mudah dipahami, dan lebih mudah dikembangkan.

### Struktur Folder

```text
TP6DPBO2526C2/
├── src/
│   ├── App.java
│   ├── MenuPanel.java
│   ├── View.java
│   ├── Logic.java
│   ├── Player.java
│   ├── Pipe.java
│   ├── Sound.java
│   └── assets/
│       ├── background.png
│       ├── bird.png
│       ├── upperPipe.png
│       ├── lowerPipe.png
│       ├── jump.wav
│       ├── score.wav
│       └── hit.wav
├── Dokumentasi/
│   ├── tampilan menu.png
│   ├── game mulai sampe gameover.mp4
└── README.md
```

### Tanggung Jawab Setiap Class

| Class | Tanggung Jawab |
|---|---|
| `App` | Menjadi titik awal program. Class ini membuat jendela utama (`JFrame`), mengatur perpindahan halaman menggunakan `CardLayout`, serta menghubungkan halaman menu dengan halaman permainan. |
| `MenuPanel` | Menampilkan halaman utama atau Main Menu. Di dalamnya terdapat dua tombol, yaitu Mulai Permainan dan Keluar. |
| `View` | Mengatur tampilan permainan menggunakan `JPanel`. Class ini bertugas menggambar background, burung, pipa, skor, serta tampilan Game Over. |
| `Logic` | Menjadi pusat pengaturan jalannya permainan. Di sini terdapat game loop, gravitasi, pergerakan burung dan pipa, deteksi tabrakan, penghitungan skor, serta proses restart. |
| `Player` | Menyimpan data burung, seperti posisi, ukuran, gambar, dan kecepatan gerak vertikal. |
| `Pipe` | Menyimpan data pipa, seperti posisi, ukuran, gambar, kecepatan gerak horizontal, dan status apakah pipa sudah dilewati burung. |
| `Sound` | Membantu mengatur pemutaran efek suara, seperti suara burung melompat, mendapatkan skor, dan menabrak pipa. |

### Diagram Class

Diagram class digunakan untuk memperlihatkan hubungan antara class yang ada di dalam program. Setiap class memiliki tanggung jawab tersendiri, tetapi tetap saling berhubungan untuk menjalankan permainan.

Class `Logic` mengimplementasikan `ActionListener` dan `KeyListener`. `ActionListener` digunakan untuk menjalankan proses permainan melalui `Timer`, sedangkan `KeyListener` digunakan untuk membaca input keyboard dari pemain.

Sementara itu, `View` dan `Logic` saling berhubungan. `Logic` menyediakan data permainan yang dibutuhkan oleh `View` untuk ditampilkan. Sebaliknya, `Logic` juga memanggil `view.repaint()` ketika tampilan perlu diperbarui dan `view.setScore()` ketika skor berubah.

### Fitur yang Tersedia

**A. Fitur Wajib**

1. **Game Over:** Permainan berakhir ketika burung menabrak pipa atau jatuh hingga menyentuh batas bawah frame.
2. **Restart:** Pemain dapat memulai ulang permainan dengan menekan tombol `R` setelah Game Over.
3. **Tampilan Skor:** Skor ditampilkan menggunakan komponen `JLabel`.
4. **Penambahan Skor:** Skor bertambah satu poin setiap burung berhasil melewati sepasang pipa.
5. **Main Menu:** Terdapat halaman utama dengan dua pilihan, yaitu Mulai Permainan dan Keluar.

**B. Fitur Bonus**

1. **Efek Suara:** Terdapat suara ketika burung melompat, mendapatkan skor, dan mengalami tabrakan. Audio menggunakan format `.wav`.

**C. Fitur Tambahan**

Selain fitur utama, terdapat beberapa tambahan untuk membuat permainan lebih menarik:

1. **Background Bergambar:** Menggunakan gambar sebagai latar belakang permainan agar tampilan tidak monoton.
2. **High Score:** Menyimpan skor tertinggi selama program masih berjalan dan menampilkannya ketika Game Over.
3. **Peningkatan Kecepatan Pipa:** Kecepatan pipa akan meningkat setiap skor mencapai kelipatan 5, dengan batas kecepatan maksimal -6.
4. **Persiapan Sebelum Bermain:** Burung tidak langsung bergerak ketika permainan dimulai. Pemain harus menekan spasi terlebih dahulu untuk memulai permainan.
5. **Tampilan Game Over:** Menambahkan lapisan gelap transparan agar informasi Game Over lebih mudah terlihat.
6. **Pengelolaan Pipa:** Pipa yang sudah keluar dari layar akan dihapus dari `ArrayList` agar tidak terus menumpuk selama permainan berlangsung.

---

## 2. Penjelasan Alur Program

Pada bagian ini akan dijelaskan bagaimana setiap class bekerja dan saling berhubungan, mulai dari program pertama kali dijalankan sampai permainan berakhir dan dimulai kembali.

### Alur Umum Program

Secara umum, permainan dimulai dari halaman Main Menu. Setelah pemain memilih Mulai Permainan, program berpindah ke halaman game. Selanjutnya, `Logic` mengatur seluruh proses permainan, sedangkan `View` menampilkan hasilnya ke layar.

### Alur Detail Program

**1. Inisialisasi Program (`App`)**

Ketika program dijalankan, class `App` akan membuat jendela utama menggunakan `JFrame`. Di dalamnya terdapat sebuah container yang menggunakan `CardLayout`.

`CardLayout` digunakan agar program dapat memiliki beberapa halaman dalam satu jendela. Pada program ini terdapat dua halaman, yaitu `MenuPanel` dengan nama kartu `"menu"` dan `View` dengan nama kartu `"game"`.

Saat pertama kali dijalankan, halaman yang ditampilkan adalah Main Menu.

**2. Main Menu (`MenuPanel`)**

Pada halaman utama terdapat dua tombol yang dapat dipilih pemain:

- **Mulai Permainan:** Berpindah ke halaman game, memanggil `logika.startGame()`, kemudian meminta fokus keyboard agar input dari pemain dapat diterima oleh `View`.
- **Keluar:** Menutup program menggunakan `System.exit(0)`.

Dengan adanya Main Menu, pemain tidak langsung masuk ke permainan ketika program pertama kali dijalankan.

**3. Game Loop (`Logic`)**

Class `Logic` mengatur jalannya permainan menggunakan dua buah `Timer`.

- `gameLoop`: Berjalan sekitar 60 kali per detik. Timer ini bertugas memperbarui posisi burung dan pipa melalui method `move()`, kemudian meminta `View` menggambar ulang tampilan menggunakan `view.repaint()`.
- `pipesCooldown`: Berjalan setiap 1,5 detik untuk memanggil `placePipes()`, yaitu method yang bertugas membuat sepasang pipa baru.

Dengan cara ini, pergerakan objek dan kemunculan pipa dapat berjalan secara otomatis tanpa harus menunggu input pemain.

**4. Pergerakan Burung dan Pipa (`move()`)**

Method `move()` bertanggung jawab untuk memperbarui posisi objek selama permainan berlangsung.

- **Pergerakan burung:** Gravitasi akan menambah kecepatan vertikal burung secara bertahap. Setelah itu, posisi Y burung diperbarui berdasarkan kecepatannya.
- **Pergerakan pipa:** Setiap pipa akan bergerak ke arah kiri sesuai nilai `pipeVelocityX`.
- **Kondisi awal:** Jika pemain belum menekan spasi pertama (`started == false`), proses pergerakan belum dijalankan.

Dengan adanya gravitasi, burung akan terus jatuh jika pemain tidak memberikan input lompatan.

**5. Pembuatan Pipa (`placePipes()`)**

Method `placePipes()` digunakan untuk membuat rintangan baru.

Posisi vertikal pipa atas ditentukan secara acak. Setelah itu, pipa bawah ditempatkan dengan jarak tertentu dari pipa atas sehingga terbentuk celah yang dapat dilewati burung.

Kedua pipa tersebut kemudian dimasukkan ke dalam `ArrayList<Pipe>` agar posisi dan pergerakannya dapat dikelola oleh program.

**6. Deteksi Tabrakan**

Untuk mengetahui apakah burung menabrak pipa, program menggunakan metode AABB (*Axis-Aligned Bounding Box*).

Sederhananya, setiap objek dianggap memiliki kotak pembatas. Jika kotak pembatas burung bertumpuk dengan kotak pembatas pipa pada sumbu X dan Y, berarti terjadi tabrakan.

Selain itu, permainan juga akan berakhir ketika bagian bawah burung menyentuh batas bawah frame.

**7. Penghitungan Skor**

Skor akan bertambah satu poin ketika burung berhasil melewati sepasang pipa.

Untuk mencegah skor bertambah dua kali pada satu pasangan pipa, program hanya menghitung pipa bagian atas. Setiap pipa memiliki status `passed` yang digunakan untuk mengetahui apakah pipa tersebut sudah dihitung atau belum.

Setelah skor bertambah, nilainya akan diperbarui pada `JLabel`. Selain itu, setiap skor mencapai kelipatan 5, kecepatan pipa akan meningkat hingga mencapai batas maksimal -6.

**8. Game Over (`endGame()`)**

Ketika terjadi tabrakan atau burung jatuh hingga batas bawah frame, method `endGame()` akan dijalankan.

Pada tahap ini:

- Status `gameOver` diubah menjadi `true`.
- Kedua timer dihentikan agar permainan tidak lagi berjalan.
- Background music (BGM) dihentikan.
- Efek suara tabrakan diputar.
- Tampilan Game Over ditampilkan oleh `View`.

Informasi yang ditampilkan meliputi skor yang berhasil diperoleh, high score, dan petunjuk untuk menekan tombol `R` jika ingin bermain kembali.

**9. Restart Permainan (`resetGame()`)**

Ketika pemain menekan tombol `R` setelah Game Over, method `resetGame()` akan dijalankan untuk mengembalikan kondisi permainan seperti semula.

Beberapa hal yang diatur ulang antara lain:

- Posisi burung dikembalikan ke posisi awal.
- Kecepatan vertikal burung diatur kembali.
- Semua pipa yang ada di dalam `ArrayList` dihapus.
- Skor dikembalikan menjadi 0.
- Kecepatan pipa dikembalikan ke kecepatan awal.
- Status permainan diubah agar pemain dapat memulai kembali dengan menekan spasi.

Dengan begitu, pemain dapat mengulang permainan tanpa perlu menutup dan menjalankan program dari awal.

**10. Urutan Menggambar Tampilan (`View.draw()`)**

Method `draw()` bertugas menggambar seluruh objek permainan. Urutan menggambar perlu diperhatikan karena objek yang digambar lebih dahulu akan berada di belakang objek berikutnya.

Urutan penggambarannya adalah:

1. **Background:** Digambar terlebih dahulu sebagai latar belakang.
2. **Pipa:** Digambar di atas background sebagai rintangan permainan.
3. **Burung:** Digambar setelah pipa agar terlihat berada di bagian depan.
4. **Lapisan Game Over:** Ditampilkan ketika status `gameOver` bernilai `true`, sehingga layar permainan menjadi lebih gelap dan informasi Game Over terlihat jelas.

---

## 3. Kontrol Permainan

Berikut adalah tombol yang digunakan untuk memainkan game Flappy Bird:

| Tombol | Fungsi |
|---|---|
| `Spasi` | Membuat burung melompat. Tekanan pertama juga digunakan untuk memulai pergerakan permainan. |
| `R` | Memulai ulang permainan setelah Game Over. |

---

## 4. Kesimpulan

Melalui pembuatan game Flappy Bird menggunakan Java Swing ini, saya belajar bagaimana menerapkan konsep pemrograman berorientasi objek dalam sebuah program berbasis GUI.

Setiap class memiliki tanggung jawab yang berbeda, mulai dari mengatur data objek, menampilkan tampilan permainan, menjalankan logika permainan, hingga mengelola audio. Semua bagian tersebut saling bekerja sama sehingga permainan dapat berjalan sesuai dengan fungsinya.

Selain itu, melalui tugas ini saya juga mempelajari penggunaan `JFrame`, `JPanel`, `CardLayout`, `Timer`, `ArrayList`, serta penerapan event handling menggunakan `ActionListener` dan `KeyListener`.

Dengan demikian, tugas ini menjadi salah satu latihan untuk memahami bagaimana konsep OOP dapat diterapkan dalam pembuatan aplikasi yang memiliki tampilan dan interaksi dengan pengguna.
