package com.whz.maven.plugin;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

/**
 * <p>
 * Hello目标插件
 * </p>
 *
 * @author: laiqi
 * @since: 2021-12-01
 */
@Mojo(name = "hello")
public class HelloMojo extends AbstractMojo {

    @Parameter(property = "project", defaultValue = "${project}")
    private MavenProject project;

    @Override
    public void execute() {
        getLog().info("你好,我的第一个maven插件!");

        // 将项目依赖打印出来
        List<Dependency> dependencies = project.getDependencies();
        for (Dependency d : dependencies) {
            getLog().info("依赖: " + d.getGroupId() + ":" + d.getArtifactId() + ":" + d.getVersion());
        }
    }

}