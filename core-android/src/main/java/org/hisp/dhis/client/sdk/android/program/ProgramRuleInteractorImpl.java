/*
 * Copyright (c) 2016, University of Oslo
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.hisp.dhis.client.sdk.android.program;

import org.hisp.dhis.client.sdk.android.api.utils.DefaultOnSubscribe;
import org.hisp.dhis.client.sdk.core.common.controllers.SyncStrategy;
import org.hisp.dhis.client.sdk.core.program.ProgramRuleController;
import org.hisp.dhis.client.sdk.core.program.ProgramRuleService;
import org.hisp.dhis.client.sdk.models.program.Program;
import org.hisp.dhis.client.sdk.models.program.ProgramRule;
import org.hisp.dhis.client.sdk.models.program.ProgramStage;

import java.util.List;
import java.util.Set;

import rx.Observable;

public class ProgramRuleInteractorImpl implements ProgramRuleInteractor {
    private final ProgramRuleService programRuleService;
    private final ProgramRuleController programRuleController;

    public ProgramRuleInteractorImpl(ProgramRuleService programRuleService,
                                     ProgramRuleController programRuleController) {
        this.programRuleService = programRuleService;
        this.programRuleController = programRuleController;
    }

    @Override
    public Observable<ProgramRule> get(final String uid) {
        return Observable.create(new DefaultOnSubscribe<ProgramRule>() {
            @Override
            public ProgramRule call() {
                return programRuleService.get(uid);
            }
        });
    }

    @Override
    public Observable<ProgramRule> get(final long id) {
        return Observable.create(new DefaultOnSubscribe<ProgramRule>() {
            @Override
            public ProgramRule call() {
                return programRuleService.get(id);
            }
        });
    }

    @Override
    public Observable<List<ProgramRule>> list() {
        return Observable.create(new DefaultOnSubscribe<List<ProgramRule>>() {
            @Override
            public List<ProgramRule> call() {
                return programRuleService.list();
            }
        });
    }

    @Override
    public Observable<List<ProgramRule>> list(final ProgramStage programStage) {
        return Observable.create(new DefaultOnSubscribe<List<ProgramRule>>() {
            @Override
            public List<ProgramRule> call() {
                return programRuleService.list(programStage);
            }
        });
    }

    @Override
    public Observable<List<ProgramRule>> list(final Program program) {
        return Observable.create(new DefaultOnSubscribe<List<ProgramRule>>() {
            @Override
            public List<ProgramRule> call() {
                return programRuleService.list(program);
            }
        });
    }

    @Override
    public Observable<List<ProgramRule>> pull() {
        return pull(SyncStrategy.DEFAULT);
    }

    @Override
    public Observable<List<ProgramRule>> pull(final Set<String> uids) {
        return pull(SyncStrategy.DEFAULT, uids);
    }

    @Override
    public Observable<List<ProgramRule>> pull(List<Program> programs) {
        return pull(SyncStrategy.DEFAULT, programs);
    }

    @Override
    public Observable<List<ProgramRule>> pull(final SyncStrategy syncStrategy) {
        return Observable.create(new DefaultOnSubscribe<List<ProgramRule>>() {
            @Override
            public List<ProgramRule> call() {
                programRuleController.pull(syncStrategy);
                return programRuleService.list();
            }
        });
    }

    @Override
    public Observable<List<ProgramRule>> pull(final SyncStrategy syncStrategy,
                                              final Set<String> uids) {
        return Observable.create(new DefaultOnSubscribe<List<ProgramRule>>() {
            @Override
            public List<ProgramRule> call() {
                programRuleController.pull(syncStrategy, uids);
                return programRuleService.list(uids);
            }
        });
    }

    @Override
    public Observable<List<ProgramRule>> pull(final SyncStrategy syncStrategy,
                                              final List<Program> programs) {
        return Observable.create(new DefaultOnSubscribe<List<ProgramRule>>() {
            @Override
            public List<ProgramRule> call() {
                programRuleController.pull(syncStrategy, programs);
                return programRuleService.list(programs);
            }
        });
    }
}
