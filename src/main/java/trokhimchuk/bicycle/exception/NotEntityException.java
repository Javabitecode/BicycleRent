package trokhimchuk.bicycle.exception;

public class NotEntityException extends Exception{
    public NotEntityException(Long userID) {
        super("Пользователь" + userID + "не найден. Проверьте данные запроса");
    }
}
