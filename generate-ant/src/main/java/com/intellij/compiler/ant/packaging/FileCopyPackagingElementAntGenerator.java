/*
 * Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */

package com.intellij.compiler.ant.packaging;

import com.intellij.compiler.ant.Generator;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.packaging.elements.AntCopyInstructionCreator;
import com.intellij.packaging.elements.ArtifactAntGenerationContext;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.elements.FileCopyPackagingElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class FileCopyPackagingElementAntGenerator extends PackagingElementAntGenerator<FileCopyPackagingElement> {
  @NotNull
  @Override
  public List<? extends Generator> computeAntInstructions(@NotNull FileCopyPackagingElement packagingElement, @NotNull PackagingElementResolvingContext resolvingContext, @NotNull AntCopyInstructionCreator creator, @NotNull ArtifactAntGenerationContext generationContext, @NotNull ArtifactType artifactType) {
    if (packagingElement.isDirectory()) {
      return Collections.emptyList();
    }
    final String path = generationContext.getSubstitutedPath(packagingElement.getFilePath());
    return Collections.singletonList((Generator)creator.createFileCopyInstruction(path, packagingElement.getOutputFileName()));
  }
}
