// Copyright 2017 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.skyframe.serialization;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import java.io.IOException;

/**
 * Generic object serialization/deserialization. Implementations should serialize values
 * deterministically.
 */
public interface ObjectCodec<T> extends BaseCodec<T> {

  /**
   * Serializes {@code obj}, inverse of {@link #deserialize}.
   *
   * @param context {@link SerializationContext} providing additional information to the
   *     serialization process
   * @param obj the object to serialize
   * @param codedOut the {@link CodedOutputStream} to write this object into. Implementations need
   *     not call {@link CodedOutputStream#flush()}, this should be handled by the caller.
   * @throws SerializationException on failure to serialize
   * @throws IOException on {@link IOException} during serialization
   */
  void serialize(SerializationContext context, T obj, CodedOutputStream codedOut)
      throws SerializationException, IOException;

  /**
   * Deserializes from {@code codedIn}, inverse of {@link #serialize}.
   *
   * @param context {@link DeserializationContext} for providing additional information to the
   *     deserialization process.
   * @param codedIn the {@link CodedInputStream} to read the serialized object from
   * @return the object deserialized from {@code codedIn}
   * @throws SerializationException on failure to deserialize
   * @throws IOException on {@link IOException} during deserialization
   */
  T deserialize(DeserializationContext context, CodedInputStream codedIn)
      throws SerializationException, IOException;
}
