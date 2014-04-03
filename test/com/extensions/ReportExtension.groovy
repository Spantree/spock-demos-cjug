package com.extensions

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

/**
 * This is oen of the examples pillaged from peter n. example code from 2011 which provides
 * an example of spock extensions
 *
 */
class ReportExtension extends AbstractAnnotationDrivenExtension<Report> {
    @Override
    void visitSpecAnnotation(Report annotation, SpecInfo spec) {
        spec.addListener(new AbstractRunListener() {
            Logger log = LoggerFactory.getLogger(spec.filename.replaceAll(/\.groovy$/, ''))
            @Override
            void afterFeature(FeatureInfo feature) {
                log.info "Feature: ${feature.name}"
                feature.blocks.eachWithIndex { block, i ->
                    log.info " ${i} ${block.kind}"
                    for (text in block.texts) {
                        log.info "  ${text}"
                    }
                }
            }
        })
    }
}
