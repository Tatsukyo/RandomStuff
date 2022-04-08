package testdao;

import testdao.dao.DaoFactory;
import testdao.dao.UserDao;
import testdao.dto.User;

/**
 *
 * @author Alexis TRAN
 */
public class Test {
    public static void main(String[] args) {
        UserDao lUserDao = DaoFactory.getInstance().getUserDao();
        
        lUserDao.findUser(1);
        lUserDao.findUser(10);
        lUserDao.getUsers();
        
        User lUserToAdd = new User();
        lUserToAdd.setFirstname("Alexis");
        lUserToAdd.setLastname("TRAN");
        lUserDao.addUser(lUserToAdd);
        
        lUserDao.removeUser(lUserToAdd.getId());
    }
}
