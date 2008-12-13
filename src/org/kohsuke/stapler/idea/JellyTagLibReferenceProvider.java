package org.kohsuke.stapler.idea;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.stapler.idea.psi.ref.TagReference;

/**
 * @author Kohsuke Kawaguchi
 */
public class JellyTagLibReferenceProvider extends PsiReferenceProvider {
    /*
        The basic idea of ReferenceProvider is to create a reference speculatively,
        then the reference object will later try to find the target.
     */
    @NotNull
    public PsiReference[] getReferencesByElement(@NotNull PsiElement e, @NotNull ProcessingContext processingContext) {
        if (e instanceof XmlTag) {
            XmlTag t = (XmlTag)e;
            if(TagReference.isApplicable(t))
                return new PsiReference[] {new TagReference(t)};
        }
        return PsiReference.EMPTY_ARRAY;
    }
}
