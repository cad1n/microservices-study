package microservices.hrworker.resources;

import microservices.hrworker.entities.Worker;
import microservices.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);
    private final Environment env;
    private final WorkerRepository repository;

    public WorkerResource(Environment env, WorkerRepository repository) {
        this.env = env;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        if (logger.isDebugEnabled()) {
            logger.info("PORT = {} ", env.getProperty("local.server.port"));
        }

        Worker obj = repository.findById(id).stream().findFirst().orElse(null);
        return ResponseEntity.ok(obj);
    }
}
