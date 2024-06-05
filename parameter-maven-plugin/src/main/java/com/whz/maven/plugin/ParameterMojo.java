package com.whz.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 参数插件测试
 *
 * @author: laiqi
 * @since: 2021-12-02
 */
@Mojo(name = "parameter")
public class ParameterMojo extends AbstractMojo {

    @Parameter(defaultValue = "Hello World!")
    private String greeting;
    @Parameter
    private boolean myBoolean;
    @Parameter
    private Integer myInteger;
    @Parameter
    private File myFile;
    @Parameter
    private ColorEnum myColor;
    @Parameter
    private String[] myArray;
    @Parameter
    private List myList;
    @Parameter
    private Map myMap;
    @Parameter
    private Properties myProperties;
    @Parameter
    private Person person;

    @Override
    public void execute() {
        this.getLog().info(toString());
    }

    @Override
    public String toString() {
        return "ParameterMojo{" +
                "greeting='" + greeting + '\'' +
                ", myBoolean=" + myBoolean +
                ", myInteger=" + myInteger +
                ", myFile=" + myFile +
                ", myColor=" + myColor +
                ", myArray=" + Arrays.toString(myArray) +
                ", myList=" + myList +
                ", myMap=" + myMap +
                ", myProperties=" + myProperties +
                ", person=" + person +
                '}';
    }
}