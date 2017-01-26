/*
 * The MIT License
 *
 * Copyright 2017 shinse.tanaka.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mrd.shinse.task.manager.application.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import mrd.shinse.task.manager.application.TaskService;
import mrd.shinse.task.manager.model.Task;
import mrd.shinse.task.manager.model.TaskContent;
import mrd.shinse.task.manager.model.repository.TaskRepository;

/**
 *
 * @author shinse.tanaka
 */
@Stateless
public class DefaultTaskService implements TaskService, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    TaskRepository taskRepository;

    @Override
    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void addTask() {
        Task newTask = new Task();
        Random rdm = new Random();
        newTask.setId(rdm.nextInt(100));
        newTask.setContent(new TaskContent("title", "this is info."));
        taskRepository.store(newTask);
    }

    @Override
    public void update(List<Task> tasks) {
        taskRepository.store(tasks);
    }
}
