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
package mrd.shinse.task.manager.interfaces.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import mrd.shinse.task.manager.interfaces.facade.TaskListFacade;
import mrd.shinse.task.manager.model.Task;
import org.primefaces.event.RowEditEvent;

/**
 * ManagedBean of Task List View.
 *
 * @author shinse.tanaka
 */
@Named(value = "taskListBean")
@Dependent
public class TaskListBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<Task> tasks;
    @Inject
    private TaskListFacade taskListFacade;

    /**
     * Creates a new instance of TaskListBean
     */
    public TaskListBean() {
    }

    @PostConstruct
    public void init() {
        this.tasks = taskListFacade.listAllTasks();
    }

    public void addTask() {
        taskListFacade.addTask();
    }

    public void update() {
        taskListFacade.update(tasks);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Taskが更新されました", ((Task) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("更新がキャンセルされました", ((Task) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
