# Клиентская часть системы "Умный дом"

---

## Используемые технологии

- **Docker/Docker-compose** - используется для контейнеризации приложения и более удобного запуска пользователем
- **Spring Boot 3** - фреймворк для разработки backend-приложений на Java
- **PostgreSQL** - реляционная база данных
- **Liquibase** - система миграции баз данных
- **Pi4J** - библиотека для взаимодействия с пинами на плате Raspberry Pi 3B+
- **Swagger** - система документирования API

---

## Обоснование выбора технологий и удобство их использования
1. **Docker/Docker-compose**
   * **Обоснование**: Контейнеризация позволяет изолировать приложение и его зависимости,
     что упрощает развертывание и масштабирование.
   * **Удобство**: Docker-compose позволяет легко управлять многоконтейнерными приложениями,
     что упрощает процесс разработки и тестирования.
2. **Spring Boot 3**
   * **Обоснование**: Spring Boot предоставляет мощные инструменты для быстрой разработки и
     масштабирования Java-приложений.
   * **Удобство**: Spring Boot 3 включает в себя множество полезных библиотек и инструментов,
     что ускоряет процесс разработки и упрощает управление зависимостями.
3. **PostgreSQL**
   * **Обоснование**: PostgreSQL является мощной и надежной реляционной базой данных, поддерживающей
     сложные запросы и транзакции.
   * **Удобство**: PostgreSQL хорошо интегрируется с Spring Boot и поддерживает широкий спектр функций,
     необходимых для работы с данными.
4. **Liquibase**
   * **Обоснование**: Liquibase обеспечивает автоматизацию миграций базы данных, что упрощает управление изменениями схемы.
   * **Удобство**: Liquibase интегрируется с Spring Boot и позволяет легко отслеживать и применять изменения в базе данных.
5. **Pi4J**
   * **Обоснование**: Pi4J предоставляет удобный интерфейс для взаимодействия с GPIO-пинами на Raspberry Pi, что необходимо для управления устройствами "Умного дома".
   * **Удобство**: Pi4J упрощает работу с аппаратными компонентами, предоставляя высокоуровневые API для управления пинами.
6. **Swagger**
   * **Обоснование**: Swagger обеспечивает автоматическое генерирование документации для API, что упрощает взаимодействие с приложением.
   * **Удобство**: Swagger интегрируется с Spring Boot и предоставляет удобный интерфейс для тестирования и документирования API.
     Декомпозиция системы и описание модулей
     Модули системы "Умный дом"
     Модуль управления устройствами
   * **Описание**: Отвечает за управление и мониторинг устройств в системе "Умный дом".
   * **Функции**: Включение/выключение устройств, изменение настроек, мониторинг состояния.
7. **Модуль взаимодействия с пользователем**
   * **Описание**: Обеспечивает взаимодействие с пользователем через веб-интерфейс и мобильное приложение.
   * **Функции**: Отображение состояния устройств, управление устройствами, настройка уведомлений.
8. **Модуль уведомлений**
   * **Описание**: Отвечает за отправку уведомлений пользователю через различные каналы (Telegram, email и т.д.).
   * **Функции**: Отправка уведомлений о событиях, напоминаний, оповещений о состоянии системы.
9. **Модуль авторизации и аутентификации**
   * **Описание**: Обеспечивает безопасность системы, управление пользователями и их правами доступа.
   * **Функции**: Регистрация пользователей, аутентификация, управление ролями и правами доступа.
10. **Модуль данных и аналитики**
    * **Описание**: Отвечает за сбор, хранение и анализ данных о состоянии устройств и пользовательских действиях.
    * **Функции**: Сбор данных, анализ, генерация отчетов, прогнозирование.
11. **Модуль интеграции с внешними системами**
    * **Описание**: Обеспечивает интеграцию с внешними системами и сервисами (например, погодные сервисы, IoT-платформы).
    * **Функции**: Получение данных из внешних источников, отправка данных в внешние системы.
12. **Модуль управления базой данных**
    * **Описание**: Отвечает за управление базой данных, миграции, резервное копирование и восстановление данных.
    * **Функции**: Миграции базы данных, резервное копирование, восстановление данных, оптимизация запросов.