package umc.study.repository.StoreRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QStore;
import umc.study.domain.Store;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Store;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Store> query = cb.createQuery(Store.class);
        Root<Store> store = query.from(Store.class);

        // 조건을 동적으로 추가
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.equal(store.get("name"), name));
        }

        if (score != null) {
            predicates.add(cb.greaterThanOrEqualTo(store.get("score"), 4.0f));
        }

        query.select(store).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}