//package com.example.reportservice;
//
//import com.example.reportservice.entity.Customer;
//import com.example.reportservice.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.logging.Logger;
//
//@Component
//public class Sender {
//
//    private final Logger logger = Logger.getLogger("CustomerService");
//
//    private final CustomerRepository customerRepository;
//    private final KafkaTemplate<String, Customer> kafkaProducer;
//    @Autowired
//    public Sender(CustomerRepository customerRepository, KafkaTemplate<String, Customer> kafkaProducer) {
//        this.customerRepository = customerRepository;
//        this.kafkaProducer = kafkaProducer;
//    }
//    public void sendCustomersToKafka() {
//        List<Customer> customers = customerRepository.findAll();
//        logger.info("-------------------------------------12313131231");
//        for (Customer customer : customers) {
//            kafkaProducer.send("customer-topic", customer);
//            System.out.println("Сообщение отправлено" + customer);
//        }
//    }
//
//    @Scheduled(fixedRate = 30000)
//    private void d() {
//        sendCustomersToKafka();
//    }
//
//
//}
