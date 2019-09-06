package ru.od.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.od.model.MainEntity;
import ru.od.repository.MainEntityRepository;



@SpringComponent
@SpringView(name = "")
@UIScope
public class SimpleView extends Panel implements View{
    private final MainEntityRepository mainEntityRepository;
    private final VerticalLayout rootLayout = new VerticalLayout();

    @Autowired
    public SimpleView(MainEntityRepository mainEntityRepository) {
        setContent(rootLayout);
        this.mainEntityRepository = mainEntityRepository;

    }

    @Override
    public void attach() {
        super.attach();
        System.out.println("Enter");
        for (MainEntity mainEntity : mainEntityRepository.findAll()) {
            String format = String.format("Имя сущности %s", mainEntity.getName());
            System.out.println(format);
            rootLayout.addComponent(new Label("----------"));
            rootLayout.addComponent(new Label(format));
            rootLayout.addComponent(new Label(String.format("Количество элементов подсущности %d", mainEntity.getSubEntities().size())));
            rootLayout.addComponent(new Label("----------"));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        rootLayout.setSpacing(true);
        rootLayout.setMargin(true);
        setSizeFull();
        rootLayout.setSizeUndefined();
        rootLayout.setWidth("100%");
    }
}
