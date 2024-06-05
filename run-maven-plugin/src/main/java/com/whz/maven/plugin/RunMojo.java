package com.whz.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 * 手动实现打包之后自动运行的插件
 * </p>
 *
 * @author: laiqi
 * @since: 2021-12-02
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.PACKAGE)
@Execute(phase = LifecyclePhase.PACKAGE)
public class RunMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/${project.artifactId}-${project.version}.jar")
    private String jarPath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            this.getLog().info("开始运行:" + this.jarPath);
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", this.jarPath);

            final Process process = builder.start();
            new Thread(() -> {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                try {
                    String s;
                    while ((s = reader.readLine()) != null) {
                        System.out.println(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            Runtime.getRuntime().addShutdownHook(new Thread() {

                @Override
                public void run() {
                    RunMojo.this.getLog().info("销毁中...");
                    process.destroy();
                    RunMojo.this.getLog().info("关闭钩子结束...");
                }

            });

            process.waitFor();
            this.getLog().info("Finished.");
        } catch (Exception e) {
            this.getLog().warn(e);
        }
    }
}