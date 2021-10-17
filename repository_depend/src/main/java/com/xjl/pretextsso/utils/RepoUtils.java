package com.xjl.pretextsso.utils;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.FetchResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Classname RepoUtils
 * @Description 对仓库的相关操作
 * @Date 2021/10/17 10:15
 * @Created by xjl
 */
public class RepoUtils {
    /**
     * @description 监视器
     * @param: null
     * @date: 2021/10/17 11:07
     * @return:
     * @author: xjl
     */
    private static class SimpleProgressMonitor implements ProgressMonitor {
        @Override
        public void start(int totalTasks) {
            System.out.println("Starting work on " + totalTasks + " tasks");
        }

        @Override
        public void beginTask(String title, int totalWork) {
            System.out.println("Start " + title + ": " + totalWork);
        }

        @Override
        public void update(int completed) {
            System.out.print(completed + "-");
        }

        @Override
        public void endTask() {
            System.out.println("Done");
        }

        @Override
        public boolean isCancelled() {
            return false;
        }
    }

    /**
     * @description TODO clone远端的项目
     * @param: username
     * @param: password
     * @param: remote_url
     * @param: brachname
     * @param: localrepoPath
     * @date: 2021/10/17 11:06
     * @return: org.eclipse.jgit.lib.Repository
     * @author: xjl
     */
    public static Repository CloneNewRepository(String username, String password, String remote_url, String brachname, String localrepoPath) throws Exception {
        File localPath = new File(localrepoPath);
        //判断时候文件是否存在
        if (localPath.isDirectory()) {
            String refbrachname = "refs/heads/" + brachname;
            FileRepository fileRepository = new FileRepository(localPath);
            List<Ref> call = new Git(fileRepository).branchList().call();
            if (!call.contains(refbrachname)) {
                //checnkout 远端分支
                if (CheckoutGitHubPullRequest(remote_url, localrepoPath, brachname)) {
                    System.out.println("checkout" + brachname + "分支,从远端" + remote_url);
                } else {
                    throw new Exception("checkout erros");
                }
            }
            return fileRepository;
        } else {
            //如果不存那就克隆新的仓库
            Git result = Git.cloneRepository()
                    .setURI(remote_url)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                    .setDirectory(localPath)
                    .setBranch(brachname)
                    .setProgressMonitor(new SimpleProgressMonitor())
                    .call();
            return result.getRepository();
        }
    }

    /**
     * @description TODO CheckoutGitHubPullRequest分支
     * @param: remote_url
     * @date: 2021/10/17 11:02
     * @return: boolean
     * @author: xjl
     */
    public static boolean CheckoutGitHubPullRequest(String remote_url, String localrepoPath, String branchname) throws GitAPIException {
        System.out.println("Cloning from " + remote_url + " to " + localrepoPath);
        try (Git result = Git.cloneRepository()
                .setURI(remote_url)
                .setDirectory(new File(localrepoPath))
                .setProgressMonitor(new SimpleProgressMonitor())
                .call()) {
            System.out.println("Having repository: " + result.getRepository().getDirectory());
            FetchResult fetchResult = result.fetch()
                    .setRemote(remote_url)
                    .setRefSpecs("+refs/pull/6/head:pr_6")
                    //.setRefSpecs(new RefSpec("+refs/heads/*:refs/heads/*"))
                    .call();
            System.out.println("Result when fetching the PR: " + fetchResult.getMessages());
            Ref checkoutRef = result.checkout()
                    .setName("pr_6")
                    .call();
            System.out.println("Checked out PR, now printing log, it should include two commits from the PR on top");
            Iterable<RevCommit> logs = result.log()
                    .call();
            for (RevCommit rev : logs) {
                System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
            }
        }
        return true;
    }

    /**
     * @description 本地仓库删除
     * @param: LocalRepositorypath
     * @date: 2021/10/17 11:06
     * @return: boolean
     * @author: xjl
     */
    public static boolean DeleteLocalRepository(String LocalRepositorypath) {
        try {
            FileUtils.deleteDirectory(new File(LocalRepositorypath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @description 新建本地仓库
     * @param:
     * @date: 2021/10/17 11:06
     * @return: org.eclipse.jgit.lib.Repository
     * @author: xjl
     */
    public static Repository CreateNewRepository() throws IOException {
        // prepare a new folder
        File localPath = File.createTempFile("TestGitRepository", "");
        if (!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }
        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();
        return repository;
    }
}
