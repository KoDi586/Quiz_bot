# Система команд для Telegram бота

Эта документация описывает новую систему команд для Telegram бота, которая позволяет легко создавать новые команды и автоматически регистрировать их в боте.

## Основные компоненты

### 1. Аннотация `@BotCommand`

Аннотация `@BotCommand` используется для пометки классов команд, которые должны быть автоматически зарегистрированы в боте. Она имеет один обязательный параметр `command`, который указывает имя команды (с префиксом `/`).

```java
@BotCommand(command = "/start")
public class MyCommand extends MessageCommand {
    // ...
}
```

### 2. Абстрактные классы команд

Для упрощения создания новых команд предоставляются следующие абстрактные классы:

#### `AbstractCommand`

Базовый абстрактный класс для всех команд. Реализует интерфейс `Command` и предоставляет методы для обработки как текстовых сообщений, так и callback-запросов. Используйте этот класс, если ваша команда должна обрабатывать оба типа обновлений.

```java
public abstract class AbstractCommand implements Command {
    // ...
    protected abstract void handleMessage(Update update);
    protected abstract void handleCallback(Update update);
}
```

#### `MessageCommand`

Абстрактный класс для команд, которые обрабатывают только текстовые сообщения. Наследуется от `AbstractCommand` и предоставляет удобные методы для работы с текстовыми сообщениями.

```java
public abstract class MessageCommand extends AbstractCommand {
    // ...
    protected abstract void handleMessage(Update update);
    // handleCallback() уже реализован и ничего не делает
}
```

#### `CallbackCommand`

Абстрактный класс для команд, которые обрабатывают только callback-запросы. Наследуется от `AbstractCommand` и предоставляет удобные методы для работы с callback-запросами.

```java
public abstract class CallbackCommand extends AbstractCommand {
    // ...
    protected abstract void handleCallback(Update update);
    // handleMessage() уже реализован и ничего не делает
}
```

### 3. Автоматическая регистрация команд

Класс `CommandRegistrar` автоматически находит все бины с аннотацией `@BotCommand` и регистрирует их в `CommandContainer`. Вам не нужно вручную добавлять команды в контейнер.

## Как создать новую команду

### 1. Команда для обработки текстовых сообщений

```java
@BotCommand(command = "/mycommand")
public class MyCommand extends MessageCommand {

    public MyCommand(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    protected void handleMessage(Update update) {
        long chatId = getChatId(update);
        String text = getMessageText(update);
        
        // Ваша логика обработки сообщения
        sendTextMessage(chatId, "Вы отправили: " + text);
    }
}
```

### 2. Команда для обработки callback-запросов

```java
@BotCommand(command = "/mycallback")
public class MyCallbackCommand extends CallbackCommand {

    public MyCallbackCommand(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    protected void handleCallback(Update update) {
        long chatId = getChatId(update);
        String callbackData = getCallbackData(update);
        
        // Ваша логика обработки callback-запроса
        sendTextMessage(chatId, "Вы нажали кнопку с данными: " + callbackData);
    }
}
```

### 3. Универсальная команда

```java
@BotCommand(command = "/universal")
public class UniversalCommand extends AbstractCommand {

    public UniversalCommand(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    protected void handleMessage(Update update) {
        // Логика обработки текстового сообщения
    }

    @Override
    protected void handleCallback(Update update) {
        // Логика обработки callback-запроса
    }
}
```

## Полезные методы

Все абстрактные классы команд предоставляют полезные методы для работы с обновлениями:

### `AbstractCommand`

- `getTelegramBot()` - получение экземпляра TelegramBot

### `MessageCommand`

- `getChatId(Update update)` - получение ID чата из текстового сообщения
- `getMessageText(Update update)` - получение текста сообщения
- `sendTextMessage(long chatId, String text)` - отправка текстового сообщения

### `CallbackCommand`

- `getChatId(Update update)` - получение ID чата из callback-запроса
- `getCallbackData(Update update)` - получение данных callback-запроса
- `sendTextMessage(long chatId, String text)` - отправка текстового сообщения

## Пример использования

1. Создайте новый класс команды, наследующийся от одного из абстрактных классов.
2. Добавьте аннотацию `@BotCommand` с указанием имени команды.
3. Реализуйте необходимые методы.
4. Готово! Команда будет автоматически зарегистрирована в боте.

```java
@BotCommand(command = "/hello")
public class HelloCommand extends MessageCommand {

    public HelloCommand(TelegramBot telegramBot) {
        super(telegramBot);
    }

    @Override
    protected void handleMessage(Update update) {
        long chatId = getChatId(update);
        sendTextMessage(chatId, "Привет, мир!");
    }
}
```

## Заключение

Новая система команд позволяет легко создавать новые команды для Telegram бота без необходимости вручную регистрировать их в контейнере. Она также упрощает обработку различных типов обновлений (текстовых сообщений и callback-запросов) благодаря абстрактным классам, которые предоставляют удобные методы для работы с обновлениями.