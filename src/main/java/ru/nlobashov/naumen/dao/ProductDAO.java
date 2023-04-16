package ru.nlobashov.naumen.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nlobashov.naumen.models.Product;
import ru.nlobashov.naumen.util.ProductNotFoundException;

import java.util.List;

@Component
public class ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Product> getListProducts()
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product", Product.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Product getProduct(int id) throws ProductNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product == null) throw new ProductNotFoundException();
        return session.get(Product.class, id);
    }

    @Transactional
    public void save(Product product)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void update(int id, Product updatedProduct) throws ProductNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Product currentProduct = session.get(Product.class, id);
        if (currentProduct == null) throw new ProductNotFoundException();
        currentProduct.setName(updatedProduct.getName());
        currentProduct.setDescription(updatedProduct.getDescription());
        currentProduct.setQuantity(updatedProduct.getQuantity());
    }

    @Transactional
    public void delete(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Product.class, id));
    }

    @Transactional(readOnly = true)
    public List<Product> findBySubstring(String pattern)
    {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product WHERE name LIKE :pattern or description LIKE :pattern", Product.class)
                .setParameter("pattern", "%" + pattern + "%").getResultList();
    }
}