Repsy Storage API

Bu proje, paketlerin dağıtımı ve indirilmesi işlemleri için bir Spring Boot tabanlı web uygulamasıdır. PostgreSQL veritabanı ile paket takibi yapılır ve MinIO kullanılarak nesne depolama işlemleri gerçekleştirilir. Bu API, geliştirici dostu olacak şekilde tasarlanmış ve konteyner olarak Docker ile çalıştırılabilir.

Özellikler
İki ana REST API endpoint'i: deployment ve download.

PostgreSQL veritabanı entegrasyonu ile paket takibi yapılır.

MinIO kullanılarak nesne depolama işlemleri yapılır.

2 ayrı depolama katmanı: Dosya Sistemi ve Nesne Depolama.

Spring Boot uygulaması olarak geliştirildi.

Docker ile konteynerize edilmiştir.

Geliştirici dostu, kurulum ve entegrasyon kolaydır.

Gereksinimler
Java 11 ve Maven

Docker

PostgreSQL veritabanı

MinIO nesne depolama sunucusu
