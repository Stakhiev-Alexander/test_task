package ru.od.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import ru.od.repository.MainEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringComponent
@SpringView(name = "")
@UIScope
public class SimpleView extends Panel implements View {
    private final MainEntityRepository mainEntityRepository;
    private final CssLayout rootLayout = new CssLayout();
    private static final Logger logger = LoggerFactory.getLogger(SimpleView.class);

    @Autowired
    public SimpleView(MainEntityRepository mainEntityRepository) {
        setContent(rootLayout);
        this.mainEntityRepository = mainEntityRepository;
    }

    @Override
    public void attach() {
        super.attach();
        logger.info("Enter");
        mainEntityRepository
            .findAll()
            .forEach(
                mainEntity -> {
                  StringBuilder outputMessage = new StringBuilder("\n----------\n");
                  String format =
                      outputMessage
                          .append("Имя сущности ")
                          .append(mainEntity.getName())
                          .append("\nКоличество элементов подсущности ")
                          .append(mainEntity.getSubEntities().size())
                          .append("\n----------\n")
                          .toString();
                  logger.info(format);
                  rootLayout.addComponent(new Label(format, ContentMode.PREFORMATTED));
                });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        rootLayout.setSizeUndefined();
        rootLayout.setWidth("100%");
    }
}
