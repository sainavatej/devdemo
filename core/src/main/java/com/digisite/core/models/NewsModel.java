package com.digisite.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import static com.adobe.aem.bcg.core.utils.PageUtils.appendHtmlToInternalLink;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class NewsModel {
	
	
	@ValueMapValue
	private String title;
	
	@ValueMapValue
	private String shortdesc;
	
	@ValueMapValue
	private String longdescription;
	
	 @ValueMapValue
	    private String link;

	 @ValueMapValue
	    private String linkText;

	 @ValueMapValue
	    @Default(booleanValues = false)
	    private boolean targetNewWindow;
	    
	    
	    @PostConstruct
	    public void init() {
	        link = isNotEmpty(link) ? appendHtmlToInternalLink(link) : link;
	    }
	    	    
	@ValueMapValue
	public String getTitle() {
		return title;
	}

	public String getShortdescription() {
		return shortdesc;
	}

	public String getLongdescription() {
		return longdescription;
	}

	public String getLink() {
		return link;
	}

	public String getLinkText() {
		return linkText;
	}

	public boolean isTargetNewWindow() {
		return targetNewWindow;
	}
	
	
	
	

}
