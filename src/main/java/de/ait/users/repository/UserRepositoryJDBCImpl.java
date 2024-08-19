package de.ait.users.repository;

import de.ait.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryJDBCImpl implements IUserRepository {

    //чтобы подтянут настройки бд из application.properties
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJDBCImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    //задает правила как читать строку
    private static final RowMapper<User> USER_ROW_MAPPER = (row, rowNum) -> {

        Long id = row.getLong("id");
        String name = row.getString("name");
        String email = row.getString("email");
        String password = row.getString("password");

        return new User(id, name, email, password);
    };



    @Override
    public List<User> findAll() {

        String queryStr = "SELECT * FROM t_user";

        return jdbcTemplate.query(queryStr, USER_ROW_MAPPER);
    }

    @Override
    public User save(User user) {

        if (user.getId() == null) {
            create(user);
        } else {
            update(user);
        }
        return null;
    }


    private User update(User user) {

        String queryStr = "UPDATE t_user SET name = ?, email = ?, password = ? WHERE id = ? ";

        int affectedRows = jdbcTemplate.update(queryStr, user.getName(), user.getEmail(), user.getPassword(), user.getId());

        return affectedRows == 1 ? user : null;
    }
    private User create(User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
            .usingGeneratedKeyColumns("id")
            .withTableName("t_user");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("password", user.getPassword());

        Long generatedId = jdbcInsert.executeAndReturnKey(parameters).longValue();

        user.setId(generatedId);

        return user;
    }
}
