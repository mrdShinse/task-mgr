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
package mrd.shinse.task.manager.infrastructure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import mrd.shinse.task.manager.model.Task;
import mrd.shinse.task.manager.model.repository.TaskRepository;

/**
 *
 * @author shinse.tanaka
 */
@ApplicationScoped
public class DefaultTaskRepository implements TaskRepository, Serializable {

    private static final long serialVersionUID = 1L;

    Map<Integer, Task> db = new HashMap<>();

    @Override
    public Task store(Task t) {
        return db.put(t.getId(), t);
    }

    @Override
    public void store(List<Task> t) {
        t.stream().forEach(task -> store(task));
    }

    @Override
    public List<Task> findAll() {
        return db.values().stream().collect(Collectors.<Task>toList());
    }

    @Override
    public Task findById(Integer id) {
        return db.get(id);
    }

}
