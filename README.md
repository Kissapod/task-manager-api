# Task Manager API

Backend API для управления задачами.

## Возможности

- Создание задач
- Получение задач
- Обновление задач
- Удаление задач
- Фильтрация
- Пагинация
- Сортировка
- Валидация данных

## Технологии

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL / H2
- Maven

## Примеры запросов

### Создать задачу

POST /tasks

```json
{
  "title": "Learn Spring",
  "done": false,
  "priority": "HIGH"
}
```

### Получить задачи

GET /tasks

### Фильтрация

GET /tasks?done=true

### Пагинация

GET /tasks?page=0&size=5
