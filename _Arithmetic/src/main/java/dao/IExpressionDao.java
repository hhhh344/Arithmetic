package dao;

/**
 * @author 戮漠
 *
 */
public interface IExpressionDao {
    /**
     * 唯一能调用的方法
     */
    public String generateExpression(int number, int range);
}
