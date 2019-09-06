package ru.od.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.od.model.MainEntity;
import ru.od.repository.MainEntityRepository;


@SpringComponent
@SpringView(name = "")
@UIScope
public class MainView extends Panel implements View {
    private static final int PAGE_SIZE = 10;
    private final MainEntityRepository mainEntityRepository;
    private final VerticalLayout contentLayout = new VerticalLayout();
    private final VerticalLayout rootLayout = new VerticalLayout();
    private final Button button = new Button("Загрузить еще");
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();
    private int current = 0;

    @Autowired
    public MainView(MainEntityRepository mainEntityRepository) {
        rootLayout.addComponent(contentLayout);
        horizontalLayout.addComponent(button);
        rootLayout.addComponent(horizontalLayout);
        setContent(rootLayout);
        this.mainEntityRepository = mainEntityRepository;

    }

    @Override
    public void attach() {
        super.attach();
        System.out.println("Enter");
        loadData();
        button.addClickListener(clickEvent -> loadData());
    }

    private void loadData() {
        Page<MainEntity> mainEntities = mainEntityRepository.findAll(new PageRequest(current++, PAGE_SIZE));
        for (MainEntity mainEntity : mainEntities) {
            String format = String.format("Имя сущности %s", mainEntity.getName());
            System.out.println(format);
            contentLayout.addComponent(new Label("----------"));
            contentLayout.addComponent(new Label(format));
            contentLayout.addComponent(new Label(String.format("Количество элементов подсущности %d", mainEntity.getSubEntities().size())));
            contentLayout.addComponent(new Label("----------"));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        rootLayout.setSpacing(true);
        rootLayout.setMargin(true);
        setSizeFull();
        rootLayout.setSizeUndefined();
        rootLayout.setWidth("100%");
        contentLayout.setWidth("100%");
    }
}
