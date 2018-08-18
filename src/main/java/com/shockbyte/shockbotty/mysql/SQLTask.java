package com.shockbyte.shockbotty.mysql;

import java.sql.Connection;

@FunctionalInterface
public interface SQLTask {

    void execute(Connection c);
}
