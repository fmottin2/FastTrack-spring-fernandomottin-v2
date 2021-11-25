package br.com.fasttrack.desafio.repository.helper.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import br.com.fasttrack.desafio.entity.Order;
import br.com.fasttrack.desafio.entity.StatusOrder;
import br.com.fasttrack.desafio.repository.filter.OrderFilter;

public class OrdersRepositoryImpl implements OrdersRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Order> filter(OrderFilter filter) {
		// Create CriteriaBuilder
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		Root<Order> root =  criteria.from(Order.class);
		Predicate[] predicates = createRestrictions(filter, builder, root);
		
		criteria.where(predicates);
	    criteria.select(root);
	    return manager.createQuery(criteria).getResultList();
	}
	
	private Predicate[] createRestrictions(OrderFilter filter, CriteriaBuilder builder, Root<Order> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (!ObjectUtils.isEmpty(filter.getStatus())) {
				if(StatusOrder.PROCESSED.toString().equals(filter.getStatus())){
					predicates.add(builder.equal(root.get("status"), StatusOrder.PROCESSED));
				}else if(StatusOrder.NOT_PROCESSED.toString().equals(filter.getStatus())){
					predicates.add(builder.equal(root.get("status"), StatusOrder.NOT_PROCESSED));
				}
			}
			
			if(filter.getMin_total()!=null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("total"), filter.getMin_total()));
			}
			
			if(filter.getMax_total()!=null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("total"), filter.getMax_total()));
			}
			
			if(!ObjectUtils.isEmpty(filter.getQ())){
				Predicate predicateForName = builder.like(root.get("name"), "%"+filter.getQ()+"%");
				Predicate predicateForDescription = builder.like(root.get("description"), "%"+filter.getQ()+"%");
				predicates.add(builder.or(predicateForName,predicateForDescription));
			}
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
