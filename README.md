# Impacto Technical Test (Spring Boot)

## Requirements

- **Java 21** (JDK 21)
- **Docker & Docker Compose** (Jika menggunakan Docker)

---

## Opsi 1: Menjalankan Aplikasi Secara Lokal (Tanpa Docker)

Untuk menjalankan secara lokal, Anda juga harus menjalankan database MySQL secara mandiri di mesin lokal Anda.

### Langkah-langkah:
1. Pastikan database MySQL Anda sedang berjalan di port `3306`.
2. Buat database baru bernama `impacto`.
3. Sesuaikan MySQL lokal (Username: `root`, Password kosong `""`). *Catatan: Jika berbeda, tinggal set .env atau mengubahnya di file application.yml Spring Boot.*
4. Buka terminal, arahkan ke folder utama Project ini.
5. Jalankan aplikasi menggunakan Gradle Wrapper:

   **Di Windows:**
   ```bat
   gradlew bootRun
   ```

   **Di Mac/Linux:**
   ```bash
   ./gradlew bootRun
   ```
6. Aplikasi akan berjalan dan dapat diakses di `http://localhost:8080`.

---

## Opsi 2: Menjalankan Aplikasi dengan Docker Compose (Direkomendasikan)

Ini adalah cara paling praktis karena perintah ini akan secara otomatis menyiapkan *container* untuk database MySQL dan aplikasi Spring Boot secara bersamaan tanpa perlu konfigurasi lokal tambahan.

### Langkah-langkah:
1. Buka terminal, arahkan ke folder utama Project ini.
2. Jalankan perintah berikut untuk mem-build dan menyalakan *containers* di background:
   ```bash
   docker-compose up -d --build
   ```
3. Tunggu proses build dan pull image selesai.
4. Aplikasi akan otomatis terhubung ke database `impacto_db` dan berjalan. Anda bisa mengakses aplikasi di `http://localhost:8080`.
5. Untuk melihat log jalannya aplikasi, Anda bisa menjalankan:
   ```bash
   docker-compose logs -f app
   ```

### Menghentikan Aplikasi (Docker)
Untuk menghentikan *containers*, jalankan perintah berikut di terminal:
```bash
docker-compose down
```
Jika ingin menghentikan sekaligus menghapus penyimpanan/volume database-nya, jalankan:
```bash
docker-compose down -v
```
