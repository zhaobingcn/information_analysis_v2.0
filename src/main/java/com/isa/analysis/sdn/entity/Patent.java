package com.isa.analysis.sdn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Administrator on 2017/4/18 0018.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Patent")
public class Patent {
}
