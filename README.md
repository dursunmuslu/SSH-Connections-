#SSH Connections Project

Bu proje, sanal bir makineye SSH bağlantısı kurarak komutları çalıştırabilen, PostgreSQL veritabanında kayıt altına alan ve kayıtlı cihaz/komut bilgileri üzerinden hızlı erişim sunan bir sistemdir. Backend, Spring Boot ile geliştirildi, veritabanı yönetimi için PostgreSQL kullanıldı ve Docker ile kolay kurulum sağlandı. Frontend tarafında ise React.js tercih edilmiştir.

Özellikler
SSH Bağlantısı Kurma: Sanal makinede köprü bağdaştırıcısı (bridge) ağ ayarı ile aynı ağda çalışan cihaz üzerinden SSH bağlantısı kurar.
Komut Çalıştırma: SSH bağlantısı üzerinden komut çalıştırır, çıktılarını alır ve veritabanına kaydeder.
Veritabanı Desteği: PostgreSQL veritabanı kullanılarak cihaz ve komut bilgileri güvenli şekilde saklanır.
Hızlı Komut Çalıştırma: Daha önce kaydedilen cihaz ve komut bilgileri üzerinden hızlı komut gönderimi yapılabilir.
Containerized Yapı: Docker ile uygulamanın PostgreSQL veritabanı container üzerinden çalıştırılır, hızlı ve kolay kurulum sağlanır.
Frontend: React.js ile kullanıcı dostu bir arayüz sunulur, veritabanındaki cihaz ve komut bilgilerine erişim ve komut gönderimi yapılabilir.

Kullanılan Teknolojiler
Backend: Spring Boot
Veritabanı: PostgreSQL (Docker ile container üzerinde çalıştırılır)
Frontend: React.js
Containerization: Docker ve Docker Compose
